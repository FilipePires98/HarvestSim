package fi.monitors;

import fi.utils.EndSimulationException;
import fi.utils.MonitorMetadata;
import fi.utils.StopHarvestException;
import fi.ccInterfaces.StorehouseCCInt;
import fi.farmerInterfaces.StorehouseFarmerInt;
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
 * Class for the monitor representing the Storehouse Sector of the farm.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class Storehouse implements StorehouseFarmerInt, StorehouseCCInt{
    
    //Monitor variables

    private UiAndMainControlsFI fi;
    private MonitorMetadata metadata;
    
    private ReentrantLock rl = new ReentrantLock();
    private Condition allInStorehouse = rl.newCondition();
    private Condition prepareOrder = rl.newCondition();
    
    private Map<Integer, Integer> positions;
    private List<Integer> availablePosition;
    
    private int farmersInStorehouse;
    private int farmersSelected;
    private boolean prepareOrderGiven;
    private boolean stopHarvest=false;
    private boolean endSimulation=false;
    private boolean proxyInMonitor=false;
    private int entitiesToStop=0;
    private int cornCobs=0;
    
    //Constructors
    
    /**
     * Storehouse monitor constructor.
     * @param fi UiAndMainControlsFI instance enabling the access to the farm infrastructure ui and websocket client
     * @param metadata MonitorMetadata instance containing the parameters to the current harvest run
     */
    public Storehouse(UiAndMainControlsFI fi, MonitorMetadata metadata) {
        this.fi = fi;
        this.metadata=metadata;
        farmersInStorehouse = 0;
        farmersSelected = 0;
        //farmersMetadata = new HashMap();
        prepareOrderGiven = false;
        positions=new HashMap<Integer, Integer>();
        availablePosition=new ArrayList<Integer>();
        for(int i=0; i<this.metadata.MAXNUMBERFARMERS;i++){
            availablePosition.add(i);
        }
    }
    
    //Methods executed by farmers
    
    
    /**
     * After collecting the cobs, each farmer must store the collected corn cobs in the storehouse 
     * before entering and preparing for another run.
     * If the farmer is carrying cobs the cobs are added to the cobs in the storehouse and removed from the farmer.
     * @param farmerId int containing the farmer identifier
     */
    @Override
    public void farmerStore(int farmerId){
        rl.lock();
        try {
            this.waitRandomDelay();
            this.fi.sendMessage("presentInStoring;"+farmerId);
            this.fi.presentStoringFarmer(farmerId);
            int cobs = ((Farmer)Thread.currentThread()).getCornCobs();
            this.cornCobs+=cobs;
            ((Farmer)Thread.currentThread()).setCornCobs(0);
            this.fi.updateStorehouseCornCobs(this.cornCobs);
            this.fi.sendMessage("updateStorehouseCobs;"+this.cornCobs);
            this.waitTimeout();
        } finally {
            rl.unlock();
        }
    }
    
    /**
     * Registers the entry of a farmer in the storehouse.
     * Farmers must wait for all farmers the be inside the storehouse.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            farmersInStorehouse++;
            this.selectSpot(farmerId);
            System.out.println("[Storehouse] Farmer " + farmerId + " entered.");
            while(farmersInStorehouse<this.metadata.MAXNUMBERFARMERS){
                allInStorehouse.await();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInStorehouse--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        prepareOrderGiven=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            allInStorehouse.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Storehouse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rl.unlock();
        }
    }
    
    /**
     * Farmers must wait for a prepare order given by the Control Center.
     * Farmers must execute this method after entering in the storehouse.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void farmerWaitPrepareOrder(int farmerId) throws StopHarvestException, EndSimulationException {
        rl.lock();
        try {
            this.waitRandomDelay();
            while(!prepareOrderGiven){
                prepareOrder.await();
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInStorehouse--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        prepareOrderGiven=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            farmersSelected++;
            farmersInStorehouse--;
            this.availablePosition.add(this.positions.get(farmerId));
            this.positions.remove(farmerId);
            
            if(farmersSelected==metadata.NUMBERFARMERS){
                farmersSelected = 0;
                prepareOrderGiven = false;
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Storehouse.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            rl.unlock();
        }
    }
    
    //Methods executed by Message Processor
    
    /**
     * Control Center Proxy must wait for all farmers to enter in the Storehouse to notify the Control Center.
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public void waitAllFarmersReady() throws StopHarvestException, EndSimulationException{
        rl.lock();
        try {
            this.waitRandomDelay();
            this.proxyInMonitor=true;
            while(farmersInStorehouse<metadata.MAXNUMBERFARMERS){
                allInStorehouse.await();
                
                if(this.stopHarvest){
                    this.entitiesToStop--;
                    this.proxyInMonitor=false;
                    if(entitiesToStop==0){
                        prepareOrderGiven=false;
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            this.proxyInMonitor=false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Storehouse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rl.unlock();
        }
    }
    
    /**
     * Notifies all the farmers waiting for a prepare order.
     * Simultaneously defines the characteristics of the current harvest run, which are:
     * 1. The number of selected farmers
     * 2. The number of corn cobs each farmer must collect
     * 3. The maximum number of steps each farmer may do in the path area
     * 4. The timeout to take in consideration in the path and granary areas
     * @param numberOfFarmers int containing the number of selected farmers
     * @param numberOfCornCobs int containing the number of corn cobs each farmer must collect
     * @param maxNumberOfSteps int containing the maximum number of steps each farmer may take in the path area
     * @param timeout int containing the timeout to consider in the path and granary area
     */
    @Override
    public void sendSelectionAndPrepareOrder(int numberOfFarmers, int numberOfCornCobs, int maxNumberOfSteps, int timeout) {
        rl.lock();
        try{
            this.waitRandomDelay();
            this.metadata.NUMBERFARMERS = numberOfFarmers;
            this.metadata.NUMBERCORNCOBS = numberOfCornCobs;
            this.metadata.NUMBERSTEPS = maxNumberOfSteps;
            this.metadata.TIMEOUT = timeout;
            this.prepareOrderGiven = true;
            this.prepareOrder.signalAll();
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
                    if(this.farmersInStorehouse!=0 || proxyInMonitor){
                        this.stopHarvest=true;
                        this.entitiesToStop=this.farmersInStorehouse;
                        if(proxyInMonitor){
                            this.entitiesToStop++;
                        }
                    }
                    break;
                case "endSimulation":
                    this.endSimulation=true;
                    break;
            }

            this.allInStorehouse.signalAll();
            this.prepareOrder.signalAll();
        }
        finally{
            rl.unlock();
        }
    }
    
    //Aux Methods
    
    /**
     * Selects a spot in the Storehouse position for a farmer to settle on.
     * @param farmerId int containing the farmer identifier
     */
    private void selectSpot(int farmerId){
        int randomPosition=(int)(Math.random()*this.availablePosition.size());
        this.positions.put(farmerId, availablePosition.get(randomPosition));
        this.availablePosition.remove(randomPosition);
        this.fi.presentFarmerInStorehouse(farmerId,positions.get(farmerId));
        this.fi.sendMessage("presentInStorehouse;"+farmerId+";"+positions.get(farmerId));
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