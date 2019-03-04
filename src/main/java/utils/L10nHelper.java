package utils;

import java.util.*;

public class L10nHelper {

    private final String path;

    public L10nHelper(String path) {
        this.path = path;
    }

    public String getString(String key, String lang) {
        return ResourceBundle.getBundle(path, new Locale(lang)).getString(key);
    }

}
