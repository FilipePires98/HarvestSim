package fi.farmerInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;

/**
 * Interface defining the service the Path monitor should provide to the Farmers.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface PathFarmerInt {
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerGoToGranary(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerReturn(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerGoToStorehouse(int farmerId) throws StopHarvestException, EndSimulationException;
}
