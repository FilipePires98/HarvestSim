package cc.utils;
import cc.UiAndMainControlsCC;
import common.MessageProcessor;

/**
 * Class responsible for processing all the received messages from the Farm Infrastructure.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class CCMessageProcessor implements MessageProcessor{
    
    /**
     * Instance of the Control Center whose messages are to be processed.
     */
    private UiAndMainControlsCC cc;
    
    /**
     * Class constructor where the Control Center whose messages are to be processed is defined.
     * @param cc Instance of the Control Center whose messages are to be processed.
     */
    public CCMessageProcessor(UiAndMainControlsCC cc) {
        this.cc=cc;
    }
    
    /**
     * Processes the incoming messages in a sequential manner since the Control Center needs to process each message one at a time.
     * @param message formatted message with the code of what to execute.
     */
    @Override
    public void processMessage(String message) {
        String[] processedMessage = message.split(";");
        switch(processedMessage[0]){
            case "presentInStorehouse":
                this.cc.presentFarmerInStorehouse(Integer.valueOf(processedMessage[1]), Integer.valueOf(processedMessage[2]));
                break;
            case "presentInStanding":
                this.cc.presentFarmerInStandingArea(Integer.valueOf(processedMessage[1]), Integer.valueOf(processedMessage[2]));
                break;
            case "presentInPath":
                this.cc.presentFarmerInPath(Integer.valueOf(processedMessage[1]), Integer.valueOf(processedMessage[2]), Integer.valueOf(processedMessage[3]));
                break;
            case "presentInGranary":
                this.cc.presentFarmerInGranary(Integer.valueOf(processedMessage[1]), Integer.valueOf(processedMessage[2]));
                break;
            case "presentInCollecting":
                this.cc.presentCollectingFarmer(Integer.valueOf(processedMessage[1]));
                break;
            case "presentInStoring":
                this.cc.presentStoringFarmer(Integer.valueOf(processedMessage[1]));
                break;
            case "updateGranaryCobs":
                this.cc.updateGranaryCornCobs(Integer.valueOf(processedMessage[1]));
                break;
            case "updateStorehouseCobs":
                this.cc.updateStorehouseCornCobs(Integer.valueOf(processedMessage[1]));
                break;
            case "infrastructureServerOnline":
                this.cc.initFIClient();
                break;
            case "allFarmersrReadyToStart":
                this.cc.enableStartBtn();
                break;
            case "allFarmersrReadyToCollect":
                this.cc.enableCollectBtn();
                break;
            case "allFarmersrReadyToReturn":
                this.cc.enableReturnBtn();
                break;
            case "allFarmersrReadyWaiting":
                this.cc.enablePrepareBtn();
                break;
            case "endSimulationOrder":
                this.cc.closeSocketClient();
                this.cc.close();
                break;
        }
    }
}
