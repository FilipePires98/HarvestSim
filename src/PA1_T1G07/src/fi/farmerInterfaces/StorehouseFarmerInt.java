package fi.farmerInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;

/**
 * Interface defining the service the Storehouse monitor should provide to the Farmers.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface StorehouseFarmerInt {
    public void farmerStore(int farmerId);
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerWaitPrepareOrder(int farmerId) throws StopHarvestException, EndSimulationException;
}
