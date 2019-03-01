import commons.Callback;
import commons.Command;
import commons.State;
import org.lettuce.stateTracker.StateTracker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;

//TODO: edit the bot name
public class YourBot extends TelegramLongPollingBot {

    /* bot authentication configuration */
    private static ResourceBundle authBundle = ResourceBundle.getBundle("auth/telegram-config");

    private static StateTracker stateTracker = new StateTracker(State.STATES);

    public String getBotUsername() {
        return authBundle.getString("bot-username");
    }

    public String getBotToken() {
        return authBundle.getString("bot-token");
    }

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String incomingText = update.getMessage().getText();
            User sender = update.getMessage().getFrom();

            /* manage commands */
            //TODO: add your commands

            if (incomingText.startsWith(Command.START)) {

                onCommandStart(update);

            } else if (incomingText.startsWith(Command.HELP)) {

                onCommandHelp(update);

            } else if (incomingText.startsWith(Command.SETTINGS)) {

                onCommandSettings(update);

            } else {

                /* manage plain text */
                //TODO: add your cases

                if (State.FIRST_STATE.equals(stateTracker.getStateOf(sender.getId()))) {

                    onInsertData(update);

                } else if (State.SECOND_STATE.equals(stateTracker.getStateOf(sender.getId()))) {

                    onInsertOtherData(update);

                }

                /* do nothing... or do something else */

            }

        } else if (update.hasCallbackQuery()) {

            String callbackData = update.getCallbackQuery().getData();
            User sender = update.getCallbackQuery().getFrom();

            /* manage callbacks */
            //TODO: add your callbacks

            if (callbackData.startsWith(Callback.FOO)) {

                onCallbackFoo(update);

            }

        }

    }

    /** commmand handlers **/

    private void onCommandStart(Update update) {

        Message incomingMessage = update.getMessage();
        User sender = incomingMessage.getFrom();

        /* this is the bot response*/
        SendMessage replyMessage = new SendMessage()
                .setChatId(incomingMessage.getChatId());

        try {

            //TODO: do stuff

            execute(replyMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

    private void onCommandHelp(Update update) {

        Message incomingMessage = update.getMessage();
        User sender = incomingMessage.getFrom();

        /* this is the bot response*/
        SendMessage replyMessage = new SendMessage()
                .setChatId(incomingMessage.getChatId());

        try {

            //TODO: do stuff

            execute(replyMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

    private void onCommandSettings(Update update) {

        Message incomingMessage = update.getMessage();
        User sender = incomingMessage.getFrom();

        /* this is the bot response*/
        SendMessage replyMessage = new SendMessage()
                .setChatId(incomingMessage.getChatId());

        try {

            //TODO: do stuff

            execute(replyMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

    //TODO: add your command handlers

    /** callback handlers **/

    private void onCallbackFoo(Update update) {

        Message originalMessage = update.getCallbackQuery().getMessage();
        String callbackData = update.getCallbackQuery().getData();
        User sender = update.getCallbackQuery().getFrom();

        /* this will edit the message from which the callback came from */
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                .setChatId(String.valueOf(sender.getId()))
                .setMessageId(originalMessage.getMessageId());

        /* this is the bot response*/
        SendMessage replyMessage = new SendMessage()
                .setChatId(originalMessage.getChatId());

        try {

            //TODO: do stuff

            execute(editMessageReplyMarkup);
            execute(replyMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

    //TODO: add your callback handlers

    /** data insert response **/

    private void onInsertData(Update update) {

        Message originalMessage = update.getCallbackQuery().getMessage();
        User sender = update.getCallbackQuery().getFrom();

        /* this is the bot response*/
        SendMessage replyMessage = new SendMessage()
                .setChatId(originalMessage.getChatId());

        try {

            //TODO: do stuff

            execute(replyMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

    private void onInsertOtherData(Update update) {

        Message originalMessage = update.getCallbackQuery().getMessage();
        User sender = update.getCallbackQuery().getFrom();

        /* this is the bot response*/
        SendMessage replyMessage = new SendMessage()
                .setChatId(originalMessage.getChatId());

        try {

            //TODO: do stuff

            execute(replyMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

}
