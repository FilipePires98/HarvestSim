package fi.farmerInterfaces;

import fi.utils.EndSimulationException;
import fi.utils.StopHarvestException;

/**
 * Interface defining the service the Standing monitor should provide to the Farmers.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface StandingFarmerInt {
    public void farmerEnter(int farmerId) throws StopHarvestException, EndSimulationException;
    public void farmerWaitStartOrder(int farmerId) throws StopHarvestException, EndSimulationException;
}
