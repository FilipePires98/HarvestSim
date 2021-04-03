package cc;

/**
 * Interface defining the service the Control Center provides to the exterior.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public interface UiAndMainControlsCC {
    public void presentFarmerInStorehouse(int farmerId, int position);
    public void presentFarmerInStandingArea(int farmerId, int position);
    public void presentFarmerInPath(int farmerId, int position, int column);
    public void presentFarmerInGranary(int farmerId, int position);
    public void presentCollectingFarmer(int farmerId);
    public void presentStoringFarmer(int farmerId);
    public void updateGranaryCornCobs(int actualNumber);
    public void updateStorehouseCornCobs(int actualNumber);
    public void initFIClient();
    public void enableStartBtn();
    public void enableCollectBtn();
    public void enableReturnBtn();
    public void enablePrepareBtn();
    public void closeSocketClient();
    public void close();
}
