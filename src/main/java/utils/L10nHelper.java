package utils;

import java.util.*;

public class L10nHelper {

    private final String path;

    public L10nHelper(String path) {
        this.path = path;
    }

    /**
     * A little shortcut to handle all the ResourceBundle objects with a single structure in a less verbose way.
     * (ResourceBundle chaches the bundles by default).
     */
    public String getString(String key, String lang) {
        return ResourceBundle.getBundle(path, new Locale(lang)).getString(key);
    }

}
