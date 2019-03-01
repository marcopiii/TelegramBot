package commons;

import java.util.ArrayList;
import java.util.List;

public class State {

    public static final String FIRST_STATE = "FIRST_STATE";
    public static final String SECOND_STATE = "SECOND_STATE";

    public static List<String> STATES = new ArrayList<String>();

    static {
        STATES.add(FIRST_STATE);
        STATES.add(SECOND_STATE);
    }

    /* TODO: add your states here */

}
