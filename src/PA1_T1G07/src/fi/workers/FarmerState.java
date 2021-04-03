package fi.workers;

/**
 * Enumerate class containing all possible states of execution of a Farmer Thread.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public enum FarmerState {
    INITIAL,
    PREPARE,
    WALK,
    WAITTOCOLLECT,
    COLLECT,
    WAITTORETURN,
    RETURN,
    STORE
}
