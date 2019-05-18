package utils.logger;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Logger {

    private ResourceBundle logBundle;

    public Logger(String path) {
        this.logBundle = ResourceBundle.getBundle(path);
    }

    public void log(String pattern, String... args) {
        System.out.println("[" + LocalDateTime.now().toString() + "] " + MessageFormat.format(logBundle.getString(pattern), args));
    }

}
