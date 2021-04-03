package fi.monitors;

import fi.utils.EndSimulationException;
import fi.utils.MonitorMetadata;
import fi.utils.StopHarvestException;
import fi.ccInterfaces.GranaryCCInt;
import fi.farmerInterfaces.GranaryFarmerInt;
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
 * Class for the monitor representing the Granary Sector of the farm.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class Granary implements GranaryFarmerInt, GranaryCCInt{
    
    //Monitor variables
    
    private UiAndMainControlsFI fi;
    private MonitorMetadata metadata;
    
    private ReentrantLock rl = new ReentrantLock();
    private Condition allInGranary = rl.newCondition();
    private Condition allCollected = rl.newCondition();
    private Condition waitCollectOrder = rl.newCondition();
    private Condition waitReturnOrder = rl.newCondition();
    
    private Map<Integer, Integer> positions;
    private List<Integer> availablePosition;
    
    private int farmersInGranary=0;
    private int farmersCollected=0;
    private boolean readyToCollect=false;
    private boolean readyToReturn=false;
    private boolean stopHarvest=false;
    private boolean endSimulation=false;
    private boolean proxyInMonitor=false;
    private int entitiesToStop=0;
    private int maxCornCobs=50;

    //Constructors
    
    /**
     * Granary Area monitor constructor.
     * @param fi UiAndMainControlsFI instance enabling the access to the farm infrastructure ui and websocket client
     * @param metadata MonitorMetadata instance containing the parameters to the current harvest run
     */
    public Granary(UiAndMainControlsFI fi, MonitorMetadata metadata) {
        this.fi = fi;
        this.metadata=metadata;
        positions=new HashMap<Integer, Integer>();
        availablePosition=new ArrayList<Integer>();
        for(int i=0; i<this.metadata.MAXNUMBERFARMERS;i++){
            availablePosition.add(i);
        }
    }
    
    //Methods executed by farmers
    
    /**
     * Registers the entry of a farmer in the granary area.
     * Farmers must wait for all farmers the be inside the granary area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            farmersInGranary++;
            this.selectSpot(farmerId);
            System.out.println("[Granary] Farmer " + farmerId + " entered.");
            while(farmersInGranary<metadata.NUMBERFARMERS){
                allInGranary.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInGranary--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        readyToReturn=false;
                        readyToCollect=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            allInGranary.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rl.unlock();
        }
    }

    /**
     * Farmers must wait for a collect order given by the Control Center.
     * Farmers must execute this method after entering in the granary area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerWaitCollectOrder(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try{
            this.waitRandomDelay();
            while(!this.readyToCollect){
                this.waitCollectOrder.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInGranary--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        readyToReturn=false;
                        readyToCollect=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rl.unlock();
        }
    }

    /**
     * Farmers when given the collect order must collect the number of cobs specified by the user, or less in the case the granary doesn't 
     * have that much corn cobs.
     * This task is done by the farmers one at a time.
     * Farmers must leave their position and go closer to the cobs location. After collecting, each farmer hold the number of cobs 
     * collected and must return to a empty space.
     * Farmers must wait for all the farmers to collect.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerCollect(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try{
            this.waitRandomDelay();
            this.availablePosition.add(this.positions.get(farmerId));
            this.positions.remove(farmerId);
            this.fi.presentCollectingFarmer(farmerId);
            this.fi.sendMessage("presentInCollecting;"+farmerId);
            if(this.maxCornCobs-this.metadata.NUMBERCORNCOBS>=0){
                ((Farmer)Thread.currentThread()).setCornCobs(this.metadata.NUMBERCORNCOBS);
                this.maxCornCobs-=this.metadata.NUMBERCORNCOBS;
            }else if(this.maxCornCobs>0 && this.maxCornCobs<this.metadata.NUMBERCORNCOBS){
                ((Farmer)Thread.currentThread()).setCornCobs(this.maxCornCobs);
                this.maxCornCobs-=this.maxCornCobs;
            }
            this.fi.updateGranaryCornCobs(this.maxCornCobs);
            this.fi.sendMessage("updateGranaryCobs;"+this.maxCornCobs);
            this.farmersCollected++;
            this.waitTimeout();
            this.selectSpot(farmerId);
            while(this.farmersCollected<metadata.NUMBERFARMERS){
                this.allCollected.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInGranary--;
                    farmersCollected--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        readyToReturn=false;
                        readyToCollect=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            this.allCollected.signalAll();
            this.readyToCollect=false;
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rl.unlock();
        }
    }

    /**
     * Farmers must wait for a return order given by the Control Center.
     * Farmers must execute this method after collecting the corn cobs.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerWaitReturnOrder(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try{
            this.waitRandomDelay();
            while(!this.readyToReturn){
                this.waitReturnOrder.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInGranary--;
                    farmersCollected--;
                    int cobs=((Farmer)Thread.currentThread()).getCornCobs();
                    this.maxCornCobs+=cobs;
                    this.fi.updateGranaryCornCobs(this.maxCornCobs);
                    this.fi.sendMessage("updateGranaryCobs;"+this.maxCornCobs);
                    ((Farmer)Thread.currentThread()).setCornCobs(0);
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        readyToReturn=false;
                        readyToCollect=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            this.availablePosition.add(this.positions.get(farmerId));
            this.positions.remove(farmerId);
            this.farmersCollected--;
            this.farmersInGranary--;
            if(this.farmersInGranary==0){
                this.readyToReturn=false;
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rl.unlock();
        }
    }
    
    //Methods executed by Message Processor

    /**
     * Control Center Proxy must wait for all farmers to enter in the granary to notify the Control Center.
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void waitAllFarmersReadyToCollect() throws StopHarvestException, EndSimulationException{
        rl.lock();
        try{
            this.waitRandomDelay();
            this.proxyInMonitor=true;
            while(this.farmersInGranary<metadata.NUMBERFARMERS){
                this.allInGranary.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    this.proxyInMonitor=false;
                    if(entitiesToStop==0){
                        readyToReturn=false;
                        readyToCollect=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            this.proxyInMonitor=false;
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rl.unlock();
        }
    }

    /**
     * Notifies all the farmers waiting for a collect order.
     */
    @Override
    public void sendCollectOrder() {
        rl.lock();
        try{
            this.waitRandomDelay();
            this.readyToCollect=true;
            this.waitCollectOrder.signalAll();
        }
        finally{
            rl.unlock();
        }
    }

    /**
     * Control Center Proxy must wait for all farmers to collect the corn cobs to notify the Control Center.
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void waitAllFarmersCollect() throws StopHarvestException, EndSimulationException{
        rl.lock();
        try{
            this.waitRandomDelay();
            this.proxyInMonitor=true;
            while(this.farmersCollected<metadata.NUMBERFARMERS){
                this.allCollected.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    this.proxyInMonitor=false;
                    if(entitiesToStop==0){
                        readyToReturn=false;
                        readyToCollect=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            this.proxyInMonitor=false;
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Granary.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rl.unlock();
        }
    }

    /**
     * Notifies all the farmers waiting for a return order.
     */
    @Override
    public void sendReturnOrder() {
        rl.lock();
        try{
            this.waitRandomDelay();
            this.readyToReturn=true;
            this.waitReturnOrder.signalAll();
        }
        finally{
            rl.unlock();
        }
    }

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
                    if(this.farmersInGranary!=0 || proxyInMonitor){
                        this.stopHarvest=true;
                        this.entitiesToStop=this.farmersInGranary;
                        if(proxyInMonitor){
                            this.entitiesToStop++;
                        }
                    }
                    break;
                case "endSimulation":
                    this.endSimulation=true;
                    break;
            }

            this.allCollected.signalAll();
            this.allInGranary.signalAll();
            this.waitCollectOrder.signalAll();
            this.waitReturnOrder.signalAll();
        }
        finally{
            rl.unlock();
        }
    }
    
    
    
    //Aux Methods
    
    /**
     * Selects a spot in the Granary position for a farmer to settle on.
     * @param farmerId int containing the farmer identifier
     */
    private void selectSpot(int farmerId){
        int randomPosition=(int)(Math.random()*this.availablePosition.size());
        this.positions.put(farmerId, availablePosition.get(randomPosition));
        this.availablePosition.remove(randomPosition);
        this.fi.presentFarmerInGranary(farmerId,positions.get(farmerId));
        this.fi.sendMessage("presentInGranary;"+farmerId+";"+positions.get(farmerId));
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