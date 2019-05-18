package utils.l10n;

import java.util.ArrayList;
import java.util.List;

public class Lang {

    static final String ENGLISH = "en";
    static final String ITALIAN = "it";
    static final String GERMAN = "de";

    private static List<String> LANGS = new ArrayList<>();

    static {
        LANGS.add(ENGLISH);
        LANGS.add(ITALIAN);
        LANGS.add(GERMAN);
    }

    /* TODO: add a locale for any supported language */

    public static List<String> getList() {
        return LANGS;
    }
}
