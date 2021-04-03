package fi.ccInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;
import fi.farmerInterfaces.*;

/**
 * Interface defining the service the Standing monitor should provide to the Control Center.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface StandingCCInt {
    public void control(String action);
    public void sendStartOrder();
    public void waitForAllFarmers() throws StopHarvestException, EndSimulationException;
}
