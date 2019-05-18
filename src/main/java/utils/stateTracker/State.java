package utils.stateTracker;

import java.util.ArrayList;
import java.util.List;

public class State {

    public static final String FIRST_STATE = "FIRST_STATE";
    public static final String SECOND_STATE = "SECOND_STATE";

    private static List<String> STATES = new ArrayList<>();

    static {
        STATES.add(FIRST_STATE);
        STATES.add(SECOND_STATE);
    }

    /* TODO: add your states here */

    public static List<String> getList() {
        return STATES;
    }

}
