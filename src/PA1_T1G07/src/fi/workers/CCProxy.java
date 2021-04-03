package fi.workers;

import common.SocketClient;
import fi.utils.EndSimulationException;
import fi.FarmInfrastructure;
import fi.utils.StopHarvestException;
import fi.ccInterfaces.GranaryCCInt;
import fi.ccInterfaces.PathCCInt;
import fi.ccInterfaces.StandingCCInt;
import fi.ccInterfaces.StorehouseCCInt;
import common.MessageProcessor;
import fi.UiAndMainControlsFI;

/**
 * Message processor for the Farm Infrastructure.
 * 
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class CCProxy implements MessageProcessor {

    /**
     * Internal class used to specify the life-cycle of the message processing
     * Thread.
     */
    private class ProcessingThread implements Runnable {

        /**
         * Message to be handled.
         */
        private String message;

        /**
         * Constructor for the thread definition.
         * @param message Message to be handled.
         */
        public ProcessingThread(String message) {
            this.message = message;
        }

        /**
         * Main method defining the life-cycle of the message processing Thread.
         */
        @Override
        public void run() {
            try {
                String[] processedMessage = this.message.split(";");
                switch (processedMessage[0]) {
                case "waitSimulationReady":
                    storeHouse.waitAllFarmersReady();
                    fi.sendMessage("allFarmersrReadyWaiting");
                    break;
                case "prepareOrder":
                    storeHouse.sendSelectionAndPrepareOrder(Integer.valueOf(processedMessage[1]),
                            Integer.valueOf(processedMessage[2]), Integer.valueOf(processedMessage[3]),
                            Integer.valueOf(processedMessage[4]));
                    standing.waitForAllFarmers();
                    fi.sendMessage("allFarmersrReadyToStart");
                    break;
                case "startHarvestOrder":
                    standing.sendStartOrder();
                    granary.waitAllFarmersReadyToCollect();
                    fi.sendMessage("allFarmersrReadyToCollect");
                    break;
                case "collectOrder":
                    granary.sendCollectOrder();
                    granary.waitAllFarmersCollect();
                    fi.sendMessage("allFarmersrReadyToReturn");
                    break;
                case "returnOrder":
                    granary.sendReturnOrder();
                    storeHouse.waitAllFarmersReady();
                    fi.sendMessage("allFarmersrReadyWaiting");
                    break;
                case "stopHarvestOrder":
                    storeHouse.control("stopHarvest");
                    standing.control("stopHarvest");
                    path.control("stopHarvest");
                    granary.control("stopHarvest");
                    storeHouse.waitAllFarmersReady();
                    fi.sendMessage("allFarmersrReadyWaiting");
                    break;
                case "endSimulationOrder":
                    storeHouse.control("endSimulation");
                    standing.control("endSimulation");
                    path.control("endSimulation");
                    granary.control("endSimulation");
                    fi.closeSocketClient();
                    fi.close();
                    break;
                }
            } catch (StopHarvestException ex) {
                return;
            } catch (EndSimulationException ex) {
                return;
            }
        }
    }

    /**
     * Instance of the storehouse area.
     */
    private StorehouseCCInt storeHouse;
    /**
     * Instance of the standing area.
     */
    private StandingCCInt standing;
    /**
     * Instance of the path area.
     */
    private PathCCInt path;
    /**
     * Instance of the granary area.
     */
    private GranaryCCInt granary;
    
    /**
     * Instance of the Farm Infrastructure whose messages are to be processed.
     */
    private UiAndMainControlsFI fi;

    /**
     * Class constructor where the Control Center whose messages are to be processed is defined.
     * @param cc Instance of the Control Center whose messages are to be processed.
     */
    /**
     * Class constructor where the Farm Infrastructure whose messages are to be processed and the respective Farm Areas are defined.
     * @param fi Instance of the Farm Infrastructure whose messages are to be processed.
     * @param storeHouse Instance of the storehouse area.
     * @param standing Instance of the standing area.
     * @param path Instance of the path area.
     * @param granary Instance of the granary area.
     */
    public CCProxy(UiAndMainControlsFI fi, StorehouseCCInt storeHouse, StandingCCInt standing, PathCCInt path,
            GranaryCCInt granary) {
        this.storeHouse = storeHouse;
        this.standing = standing;
        this.path = path;
        this.granary = granary;
        this.fi = fi;

    }

    /**
     * Processes the incoming messages. For each message a new Thread is started
     * since the Farm Infrastructure needs to process several messages at the same
     * time.
     * 
     * @param message string containing the message to process
     */
    @Override
    public void processMessage(String message) {
        Thread processor = new Thread(new ProcessingThread(message));
        processor.start();
    }

}
