package fi;

/**
 * Interface defining the service the Farm Infrastructure provides to the exterior.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface UiAndMainControlsFI {
    public void presentFarmerInStorehouse(int farmerId, int position);
    public void presentFarmerInStandingArea(int farmerId, int position);
    public void presentFarmerInPath(int farmerId, int position, int column);
    public void presentFarmerInGranary(int farmerId, int position);
    public void presentCollectingFarmer(int farmerId);
    public void presentStoringFarmer(int farmerId);
    public void updateGranaryCornCobs(int actualNumber);
    public void updateStorehouseCornCobs(int actualNumber);
    public void sendMessage(String message);
    public void closeSocketClient();
    public void close();
}
