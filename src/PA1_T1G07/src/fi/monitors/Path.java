package fi.monitors;

import fi.utils.EndSimulationException;
import fi.utils.MonitorMetadata;
import fi.utils.StopHarvestException;
import fi.ccInterfaces.PathCCInt;
import fi.farmerInterfaces.PathFarmerInt;
import fi.workers.Farmer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import fi.UiAndMainControlsFI;

/**
 * Class for the monitor representing the Path Sector of the farm.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */

public class Path implements PathFarmerInt, PathCCInt {

    
    /**
     * Private inner class for holding useful information for tracking farmers while moving inside the Path. 
     */
    private class ConditionAndPathDepth{
        public Condition condition;
        public int position;
        public int depth;
        
        public ConditionAndPathDepth(Condition condition, int depth, int position){
            this.condition=condition;
            this.position=position;
            this.depth=depth;
        }
    } 
    
    // Monitor variables
    
    private final UiAndMainControlsFI fi;
    private final MonitorMetadata metadata;
    
    private final ReentrantLock rl;
    private final Condition allInPath;
    
    private final List<Integer> farmersOrder;
    private Integer currentFarmerToMove=null;
    private final Integer path[][];
    private final Map<Integer, ConditionAndPathDepth> farmersMetadata;
    private final List<List<Integer>> availablePositions;
    
    private final int pathSize;
    private int farmersInPath;
    private boolean stopHarvest=false;
    private boolean endSimulation=false;
    private int entitiesToStop=0;

    //Constructors

    /**
     * Path Area monitor constructor.
     * @param fi UiAndMainControlsFI instance enabling the access to the farm infrastructure ui and websocket client
     * @param metadata MonitorMetadata instance containing the parameters to the current harvest run
     * @param pathSize int containing the path size
     */
    public Path(UiAndMainControlsFI fi, MonitorMetadata metadata, int pathSize) {
        this.rl = new ReentrantLock();
        this.allInPath = rl.newCondition();
        this.fi = fi;
        this.metadata=metadata;
        this.farmersInPath = 0;
        this.pathSize=pathSize;
        this.farmersOrder=new ArrayList();
        this.path=new Integer[pathSize][metadata.MAXNUMBERFARMERS];
        this.farmersMetadata = new HashMap();
        this.availablePositions = new ArrayList();
        
        for(int i=0; i< pathSize; i++){
            this.availablePositions.add(new ArrayList());
            for(int j=0; j<metadata.MAXNUMBERFARMERS; j++){
                this.availablePositions.get(i).add(j);
            }
        }
        
    }
    
    //Methods executed by farmers
    
    
    /**
     * Registers the entry of a farmer in the path area in the Standing-Granary direction.
     * Farmers must wait for all farmers the be inside the path area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            farmersInPath++;
            farmersOrder.add(farmerId);
            farmersMetadata.put(farmerId, new ConditionAndPathDepth(rl.newCondition(), -1, -1));
            this.selectSpot(farmerId, false, true);
            this.fi.presentFarmerInPath(farmerId,farmersMetadata.get(farmerId).position, farmersMetadata.get(farmerId).depth);
            System.out.println("[Path] Farmer " + farmerId + " entered.");
            this.waitTimeout();
            if(farmersInPath==metadata.NUMBERFARMERS){
                currentFarmerToMove=farmersOrder.get(0);
            }
            while(farmersInPath<metadata.NUMBERFARMERS){
                allInPath.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInPath--;
                    path[farmersMetadata.get(farmerId).depth][farmersMetadata.get(farmerId).position]=null;
                    this.availablePositions.get(this.farmersMetadata.get(farmerId).depth).add(farmersMetadata.get(farmerId).position);
                    this.farmersOrder.remove((Integer)farmerId);
                    this.farmersMetadata.remove(farmerId);
                    if(entitiesToStop==0){
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            allInPath.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rl.unlock();
        }
    }

    /**
     * Function containing the logic for the farmers to walk in the path in the Standing-Granary direction.
     * Farmers will go through the path making a random number of steps between 1 and the number defined by the user.
     * Farmers only move forwards. Farmers must only enter positions that are empty.
     * Farmers will be blocked in the function until they reach the end of the path.
     * Farmers must execute this method after entering in the path area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerGoToGranary(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            while(this.farmersMetadata.get(farmerId).depth<this.pathSize){

                while(this.currentFarmerToMove!=farmerId){

                    this.farmersMetadata.get(farmerId).condition.await();
                    
                    if(this.stopHarvest){
                        entitiesToStop--;
                        farmersInPath--;
                        path[farmersMetadata.get(farmerId).depth][farmersMetadata.get(farmerId).position]=null;
                        this.availablePositions.get(this.farmersMetadata.get(farmerId).depth).add(farmersMetadata.get(farmerId).position);
                        this.farmersOrder.remove((Integer)farmerId);
                        this.farmersMetadata.remove(farmerId);
                        if(entitiesToStop==0){
                            stopHarvest=false;
                        }
                        throw new StopHarvestException();
                    }
                    if(this.endSimulation){
                        throw new EndSimulationException();
                    }
                }
                this.selectSpot(farmerId, false, false);
                this.waitTimeout();
                
                if(this.farmersInPath>1){
                    this.currentFarmerToMove=this.farmersOrder.get((this.farmersOrder.indexOf(farmerId)+1)%this.farmersOrder.size());
                    this.farmersMetadata.get(this.currentFarmerToMove).condition.signalAll();
                }

                
            }
            this.farmersInPath--;
            this.farmersOrder.remove((Integer)farmerId);
            this.farmersMetadata.remove(farmerId);

        } catch (InterruptedException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rl.unlock();
        }
    }
    
    /**
     * Registers the entry of a farmer in the path area in the Granary-Standing direction.
     * Farmers must wait for all farmers the be inside the path area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerReturn(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            farmersInPath++;
            farmersOrder.add(farmerId);
            farmersMetadata.put(farmerId, new ConditionAndPathDepth(rl.newCondition(), this.pathSize, -1));
            this.selectSpot(farmerId, true, true);
            this.waitTimeout();
            if(farmersInPath==metadata.NUMBERFARMERS){
                currentFarmerToMove=farmersOrder.get(0);
            }
            
            while(farmersInPath<metadata.NUMBERFARMERS){
                allInPath.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInPath--;
                    ((Farmer)Thread.currentThread()).setCornCobs(0);
                    path[farmersMetadata.get(farmerId).depth][farmersMetadata.get(farmerId).position]=null;
                    this.availablePositions.get(this.farmersMetadata.get(farmerId).depth).add(farmersMetadata.get(farmerId).position);
                    this.farmersOrder.remove((Integer)farmerId);
                    this.farmersMetadata.remove(farmerId);
                    if(entitiesToStop==0){
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            allInPath.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rl.unlock();
        }
    }

    /**
     * Function containing the logic for the farmers to walk in the path in the Granary-Standing direction.
     * Farmers will go through the path making a random number of steps between 1 and the number defined by the user.
     * Farmers only move forwards. Farmers must only enter positions that are empty.
     * Farmers will be blocked in the function until they reach the end of the path.
     * Farmers must execute this method after entering in the path area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerGoToStorehouse(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            while(this.farmersMetadata.get(farmerId).depth>=0){
                while(this.currentFarmerToMove!=farmerId){
                    this.farmersMetadata.get(farmerId).condition.await();
                    
                    if(this.stopHarvest){
                        entitiesToStop--;
                        farmersInPath--;
                        ((Farmer)Thread.currentThread()).setCornCobs(0);
                        path[farmersMetadata.get(farmerId).depth][farmersMetadata.get(farmerId).position]=null;
                        this.availablePositions.get(this.farmersMetadata.get(farmerId).depth).add(farmersMetadata.get(farmerId).position);
                        this.farmersOrder.remove((Integer)farmerId);
                        this.farmersMetadata.remove(farmerId);
                        if(entitiesToStop==0){
                            stopHarvest=false;
                        }
                        throw new StopHarvestException();
                    }
                    if(this.endSimulation){
                        throw new EndSimulationException();
                    }
                }
                this.selectSpot(farmerId, true, false);
                this.waitTimeout();
                if(this.farmersInPath>1){
                    this.currentFarmerToMove=this.farmersOrder.get((this.farmersOrder.indexOf(farmerId)+1)%this.farmersOrder.size());
                    this.farmersMetadata.get(this.currentFarmerToMove).condition.signalAll();
                }
            }
            this.farmersInPath--;
            this.farmersOrder.remove((Integer)farmerId);
            this.farmersMetadata.remove(farmerId);
        } catch (InterruptedException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rl.unlock();
        }
    }
   

    //Methods executed by Message Processor

    /**
     * Notifies every entity in the monitor that either the harvest run has stopped or the simulation has ended. 
     * @param action string containing the action to perform
     */
    @Override
    public void control(String action) {
        rl.lock();
        try{
            this.waitRandomDelay();
            switch(action){
                case "stopHarvest":
                    if(this.farmersInPath!=0){
                        this.stopHarvest=true;
                        this.entitiesToStop=this.farmersInPath;
                    }
                    break;
                case "endSimulation":
                    this.endSimulation=true;
                    break;
            }

            this.allInPath.signalAll();
            this.farmersMetadata.keySet().forEach((key) -> {
                this.farmersMetadata.get(key).condition.signalAll();
            });
        }
        finally{
            rl.unlock();
        }
    }

    
    //Aux Methods
    
    /**
     * Selects a spot in the path area position for a farmer to settle on.
     * @param farmerId int containing the farmer identifier
     * @param first boolean representing if the farmer is in the Standing-Granary direction(false) or in the Granary-Standing direction(true) 
     */
    private void selectSpot(int farmerId, boolean reverse, boolean first){
        int numberOfSteps;
        if(first){
            numberOfSteps=1;
        }else{
            numberOfSteps=(int)Math.round(Math.random()*(metadata.NUMBERSTEPS-1))+1;
        }
        int newDepth;
        if(reverse){
            newDepth=this.farmersMetadata.get(farmerId).depth-numberOfSteps;
        }else{
            newDepth=this.farmersMetadata.get(farmerId).depth+numberOfSteps;
        }
        if(newDepth>=this.pathSize || newDepth<0){
            path[farmersMetadata.get(farmerId).depth][farmersMetadata.get(farmerId).position]=null;
            this.availablePositions.get(this.farmersMetadata.get(farmerId).depth).add(farmersMetadata.get(farmerId).position);
            farmersMetadata.get(farmerId).depth=newDepth;
            return;
        }
        int randomIndex = (int)(Math.random() * this.availablePositions.get(newDepth).size());
        int randomPosition=this.availablePositions.get(newDepth).get(randomIndex);
        if((this.farmersMetadata.get(farmerId).depth!=-1 && !reverse) || (this.farmersMetadata.get(farmerId).depth!=10 && reverse)){
            path[farmersMetadata.get(farmerId).depth][farmersMetadata.get(farmerId).position]=null;
            this.availablePositions.get(this.farmersMetadata.get(farmerId).depth).add(farmersMetadata.get(farmerId).position);
        }
        this.availablePositions.get(newDepth).remove(randomIndex);
        path[newDepth][randomPosition]=farmerId;
        farmersMetadata.get(farmerId).position=randomPosition;
        farmersMetadata.get(farmerId).depth=newDepth;

        this.fi.presentFarmerInPath(farmerId,farmersMetadata.get(farmerId).position, farmersMetadata.get(farmerId).depth);
        this.fi.sendMessage("presentInPath;"+farmerId+";"+farmersMetadata.get(farmerId).position+";"+farmersMetadata.get(farmerId).depth);

    }
    
    /**
     * Auxiliary function created to make each thread wait a random delay.
     */
    private void waitRandomDelay(){
        try {
            int randomDelay=(int)(Math.random()*(this.metadata.MAXDELAY));
            Thread.sleep(randomDelay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Storehouse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Auxiliary function created to make each thread wait the specified timeout defined by the user.
     */
    private void waitTimeout(){
        try {
            Thread.sleep(this.metadata.TIMEOUT);
        } catch (InterruptedException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
