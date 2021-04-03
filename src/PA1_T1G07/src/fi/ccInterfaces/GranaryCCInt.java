package fi.ccInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;

/**
 * Interface defining the service the Granary monitor should provide to the Control Center.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface GranaryCCInt {
    public void control(String action);
    public void waitAllFarmersReadyToCollect() throws StopHarvestException, EndSimulationException;
    public void sendCollectOrder();
    public void waitAllFarmersCollect() throws StopHarvestException, EndSimulationException;
    public void sendReturnOrder();
}