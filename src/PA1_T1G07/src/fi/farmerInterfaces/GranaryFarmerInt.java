package fi.farmerInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;

/**
 * Interface defining the service the Granary monitor should provide to the Farmers.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface GranaryFarmerInt {
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerWaitCollectOrder(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerCollect(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerWaitReturnOrder(int farmerId) throws StopHarvestException, EndSimulationException;
}
