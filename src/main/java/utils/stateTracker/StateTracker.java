package utils.stateTracker;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * This class provide a simple system to track the state of a user. Users are represented as
 * by their ids while states are identified by strings.
 * A list with possible states has to be provided on construction, then users can
 * be easily added, moved or removed.
 */
public class StateTracker {

    private final List<String> states;
    private Map<String, ConcurrentLinkedDeque<Integer>> sessions;

    /**
     * Constructor for the StateTracker
     * @param states a list with the possible states a user can be in
     */
    public StateTracker(List<String> states) {
        this.states = states;
        this.sessions = new Hashtable<>();
        for (String state : states) {
            sessions.put(state, new ConcurrentLinkedDeque<>());
        }
    }

    /**
     * Move a user from his current state to the specified one, or,
     * if the user does not exist, add a new use in the specified state.
     * @param user the user to be moved or inserted
     * @param targetState the state to be moved or inserted to
     * @return true if the user has been moved, false if it has been added
     */
    public boolean move(Integer user, String targetState) throws IllegalArgumentException {

        if (!states.contains(targetState))
            throw new IllegalArgumentException("invalid state");

        boolean isPresent = false;

        for (String existingState : this.states) {
            ConcurrentLinkedDeque<Integer> usersInThisState = sessions.get(existingState);
            if (usersInThisState.contains(user)) {
                isPresent = true;
                usersInThisState.remove(user);
            }
        }

        sessions.get(targetState).add(user);
        return isPresent;

    }

    /**
     * Remove a user from his session
     * @param user the user to be removed
     * @return true if the user has been removed, false if the user did not exist
     */
    public boolean remove(Integer user) {

        boolean isPresent = false;
        for (String existingState : this.states) {
            ConcurrentLinkedDeque<Integer> usersInThisState = sessions.get(existingState);
            if (usersInThisState.contains(user)) {
                isPresent = true;
                usersInThisState.remove(user);
            }
        }
        return isPresent;

    }

    /**
     * Get the current state of a user
     * @param user the user to fetch
     * @return the current state of the user or null if the user is not present
     */
    public String getStateOf(Integer user) {

        for (String state : this.states) {
            ConcurrentLinkedDeque<Integer> usersInThisState = sessions.get(state);
            if (usersInThisState.contains(user)) {
                return state;
            }
        }
        return null;

    }

}
