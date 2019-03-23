package utils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Logger {

    private ResourceBundle logBundle;

    public Logger(String path) {
        this.logBundle = ResourceBundle.getBundle(path);
    }

    public void log(String pattern, String... args) {
        System.out.println("[" + LocalDateTime.now().toString() + "] " + MessageFormat.format(pattern, args));
    }

}
