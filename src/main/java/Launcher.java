import bot.YourBot;
import cron.SendNotification;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

        /* Schedule tasks not related to updates via Quartz */
        try {

            /* Instantiate the job that will call the bot function */
            JobDetail jobSendNotification = JobBuilder.newJob(SendNotification.class)
                    .withIdentity("sendNotification")
                    .build();

            /* Define a trigger for the call */
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("everyMorningAt8")
                    .withSchedule(
                            CronScheduleBuilder.dailyAtHourAndMinute(8, 0)) //TODO: define your schedule
                    .build();

            /* Create a scheduler to manage triggers */
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.getContext().put("bot", bot);
            scheduler.start();
            scheduler.scheduleJob(jobSendNotification, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

}