package cron;

import bot.YourBot;

import java.util.TimerTask;

public class TriggerScheduledOperation extends TimerTask {

    private YourBot bot;

    public TriggerScheduledOperation(YourBot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        bot.scheduledOperation();
    }

}
