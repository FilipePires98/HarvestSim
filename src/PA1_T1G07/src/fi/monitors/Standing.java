package fi.monitors;

import fi.utils.EndSimulationException;
import fi.utils.MonitorMetadata;
import fi.utils.StopHarvestException;
import fi.ccInterfaces.StandingCCInt;
import fi.farmerInterfaces.StandingFarmerInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import fi.UiAndMainControlsFI;

/**
 * Class for the monitor representing the Standing Sector of the farm.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class Standing implements StandingFarmerInt, StandingCCInt {
    
    //Monitor variables

    private UiAndMainControlsFI fi;
    private MonitorMetadata metadata;
    
    private Map<Integer, Integer> positions;
    private List<Integer> availablePosition;
    
    private int farmersInStanding=0;
    private boolean startOrderGiven=false;
    private boolean stopHarvest=false;
    private boolean endSimulation=false;
    private boolean proxyInMonitor=false;
    private int entitiesToStop=0;
    
    //Constructors
    
    /**
     * Standing Area monitor constructor.
     * @param fi UiAndMainControlsFI instance enabling the access to the farm infrastructure ui and websocket client
     * @param metadata MonitorMetadata instance containing the parameters to the current harvest run
     */
    public Standing(UiAndMainControlsFI fi, MonitorMetadata metadata) {
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
     * Registers the entry of a farmer in the standing area.
     * Farmers must wait for all farmers the be inside the standing area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public synchronized void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException{
        try {
            this.waitRandomDelay();
            farmersInStanding++;
            this.selectSpot(farmerId);
            System.out.println("[Standing Area] Farmer " + farmerId + " entered.");
            while(farmersInStanding<this.metadata.NUMBERFARMERS){
                wait();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInStanding--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            notifyAll();

        } catch (InterruptedException ex) {
            Logger.getLogger(Standing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Farmers must wait for a start order given by the Control Center.
     * Farmers must execute this method after entering in the standing area.
     * @param farmerId int containing the farmer identifier
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public synchronized void farmerWaitStartOrder(int farmerId) throws StopHarvestException, EndSimulationException{
        try{
            this.waitRandomDelay();
            while(!startOrderGiven){
                wait();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    farmersInStanding--;
                    this.availablePosition.add(this.positions.get(farmerId));
                    this.positions.remove(farmerId);
                    if(entitiesToStop==0){
                        stopHarvest=false;
                    }
                    throw new StopHarvestException();
                }
                if(this.endSimulation){
                    throw new EndSimulationException();
                }
            }
            farmersInStanding--;
            this.availablePosition.add(this.positions.get(farmerId));
            this.positions.remove(farmerId);
            if(farmersInStanding==0){
                startOrderGiven=false;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Standing.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Methods executed by Message Processor
    
    /**
     * Notifies all the farmers waiting for a start order.
     */
    @Override
    public synchronized void sendStartOrder() {
        this.waitRandomDelay();
        startOrderGiven = true;
        notifyAll();
    }

    
    /**
     * Control Center Proxy must wait for all farmers to enter in the Standing area to notify the Control Center.
     * @throws fi.utils.StopHarvestException when the harvest run has stopped
     * @throws fi.utils.EndSimulationException when the simulation has ended
     */
    @Override
    public synchronized void waitForAllFarmers() throws StopHarvestException, EndSimulationException{
        try {
            this.waitRandomDelay();
            this.proxyInMonitor=true;
            while(farmersInStanding<this.metadata.NUMBERFARMERS){
                wait();
                
                if(this.stopHarvest){
                    entitiesToStop--;
                    this.proxyInMonitor=false;
                    if(entitiesToStop==0){
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
            Logger.getLogger(Standing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Notifies every entity in the monitor that either the harvest run has stopped or the simulation has ended. 
     * @param action string containing the action to perform
     */
    @Override
    public synchronized void control(String action) {
        this.waitRandomDelay();
        switch(action){
            case "stopHarvest":
                if(this.farmersInStanding!=0 || proxyInMonitor){
                    this.stopHarvest=true;
                    this.entitiesToStop=this.farmersInStanding;
                    if(proxyInMonitor){
                        this.entitiesToStop++;
                    }
                }
                break;
            case "endSimulation":
                this.endSimulation=true;
                break;
        }

        notifyAll();

    }
    
    
    
    //Aux Methods
    
    /**
     * Selects a spot in the Standing area position for a farmer to settle on.
     * @param farmerId int containing the farmer identifier
     */
    private void selectSpot(int farmerId){
        int randomPosition=(int)(Math.random()*this.availablePosition.size());
        this.positions.put(farmerId, availablePosition.get(randomPosition));
        this.availablePosition.remove(randomPosition);
        this.fi.presentFarmerInStandingArea(farmerId,positions.get(farmerId));
        this.fi.sendMessage("presentInStanding;"+farmerId+";"+positions.get(farmerId));
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
    
}