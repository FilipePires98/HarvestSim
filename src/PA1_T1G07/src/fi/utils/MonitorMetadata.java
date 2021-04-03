package fi.utils;

/**
 * Auxiliary object created to centralize some information about the simulated harvest.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class MonitorMetadata {
    
    /**
     * Maximum number of farmers for the simulation.
     */
    public int MAXNUMBERFARMERS;
    /**
     * Maximum response delay.
     */
    public int MAXDELAY;
    /**
     * Number of defined farmers.
     */
    public int NUMBERFARMERS;
    /**
     * Number fo defined corn cobs initially in the granary.
     */
    public int NUMBERCORNCOBS;
    /**
     * Maximum number of steps (positions to move forward) for the farmers when they walk in the Path monitor.
     */
    public int NUMBERSTEPS;
    /**
     * Timeout defined (in ms) for each farmer movement.
     */
    public int TIMEOUT;

    /**
     * Class constructor to define the monitor metadata to be followed by all farm areas (monitors).
     * @param numFarmers Number of defined farmers.
     * @param delay Maximum response delay.
     */
    public MonitorMetadata(int numFarmers, int delay) {
        this.MAXNUMBERFARMERS=numFarmers;
        this.MAXDELAY=delay;
    }
    
}
