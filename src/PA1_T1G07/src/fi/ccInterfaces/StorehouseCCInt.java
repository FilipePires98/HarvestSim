package fi.ccInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;
import fi.farmerInterfaces.*;

/**
 * Interface defining the service the Storehouse monitor should provide to the Control Center.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface StorehouseCCInt {
    public void control(String action);
    public void sendSelectionAndPrepareOrder(int numberOfFarmers, int numberOfCornCobs, int maxNumberOfSteps, int timeout);
    public void waitAllFarmersReady() throws StopHarvestException, EndSimulationException;
}
