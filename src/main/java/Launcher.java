import bot.YourBot;
import cron.TriggerScheduledOperation;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.Timer;

public class Launcher {

    public static void main(String[] args) {

        /* Initialize Api Context */
        ApiContextInitializer.init();

        /* Instantiate Telegram Bots API */
        TelegramBotsApi botsApi = new TelegramBotsApi();

        /* Instantiate your bot */
        YourBot bot = new YourBot();

        /* Register your bot to poll updates */
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /* Instantiate tasks not related to updates */
        TriggerScheduledOperation task = new TriggerScheduledOperation(bot);

        /* Schedule the tasks to run periodically */
        new Timer().scheduleAtFixedRate(task, 0, 3600000);

    }

}