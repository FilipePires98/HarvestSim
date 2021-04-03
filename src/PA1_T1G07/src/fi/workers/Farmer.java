package fi.workers;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;
import fi.farmerInterfaces.GranaryFarmerInt;
import fi.farmerInterfaces.PathFarmerInt;
import fi.farmerInterfaces.StandingFarmerInt;
import fi.farmerInterfaces.StorehouseFarmerInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Definition of the Farmer Thread for the agricultural harvest.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class Farmer extends Thread {
    
    /**
     * Farmer's current state.
     */
    private FarmerState state;
    /**
     * Instance of the storeHouse area.
     */
    private StorehouseFarmerInt storeHouse;
    /**
     * Instance of the standing area.
     */
    private StandingFarmerInt standing;
    /**
     * Instance of the path area.
     */
    private PathFarmerInt path;
    /**
     * Instance of the granary area.
     */
    private GranaryFarmerInt granary;
    /**
     * Farmer Identifier.
     */
    private final int id;
    /**
     * Current amount of corn cobs held by the farmer.
     */
    private int cornCobs=0;

    /**
     * Class constructor to define the farmer identifier and the farm areas where he will work.
     * @param id Farmer Identifier.
     * @param storeHouse Instance of the storeHouse area.
     * @param standing Instance of the standing area.
     * @param path Instance of the path area.
     * @param granary Instance of the granary area.
     */
    public Farmer(int id,StorehouseFarmerInt storeHouse,StandingFarmerInt standing,PathFarmerInt path,GranaryFarmerInt granary) {
        this.storeHouse=storeHouse;
        this.standing=standing;
        this.path=path;
        this.granary=granary;
        this.id=id;
    }
    
    /**
     * Method containing the life-cycle of the Farmer thread.
     */
    @Override
    public void run() {
        while(true){
            try {
                this.state=FarmerState.INITIAL;
                this.storeHouse.farmerEnter(id);
                this.storeHouse.farmerWaitPrepareOrder(id);
                this.state=FarmerState.PREPARE;
                this.standing.farmerEnter(id);
                this.standing.farmerWaitStartOrder(id);
                this.state=FarmerState.WALK;
                this.path.farmerEnter(id);
                this.path.farmerGoToGranary(id);
                this.granary.farmerEnter(id);
                this.state=FarmerState.WAITTOCOLLECT;
                this.granary.farmerWaitCollectOrder(id);
                this.state=FarmerState.COLLECT;
                this.granary.farmerCollect(id);
                this.state=FarmerState.WAITTORETURN;
                this.granary.farmerWaitReturnOrder(id);
                this.state=FarmerState.RETURN;
                this.path.farmerReturn(id);
                this.path.farmerGoToStorehouse(id);
                this.state=FarmerState.STORE;
                this.storeHouse.farmerStore(id);
            } catch (StopHarvestException ex) {
                
            } catch (EndSimulationException ex) {
                System.out.println("Farmer "+this.id+" exited with success!");
                return;
            }
        }
    }
    
    /**
     * Auxiliary method to retrieve farmer's identifier.
     * @return Farmer Identifier.
     */
    public int getID(){
        return this.id;
    }

    /**
     * Auxiliary method to retrieve farmer's corn cobs.
     * @return Current amount of corn cobs held by the farmer.
     */
    public int getCornCobs() {
        return cornCobs;
    }

    /**
     * Auxiliary method to update farmer's corn cobs.
     * @param cornCobs Updated current amount of corn cobs held by the farmer.
     */
    public void setCornCobs(int cornCobs) {
        this.cornCobs = cornCobs;
    }
    
    
}
