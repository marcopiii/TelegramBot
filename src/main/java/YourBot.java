import commons.Callback;
import commons.Command;
import commons.State;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.persistence.PersistenceService;
import utils.L10nHelper;
import utils.Logger;
import utils.StateTracker;

import java.text.MessageFormat;
import java.util.ResourceBundle;

//TODO: edit the bot name
public class YourBot extends TelegramLongPollingBot {

    /* resource bundles to retrieve configurations and authentications for
     * Telegram API and any external service you may use */
    private static ResourceBundle authBundle = ResourceBundle.getBundle("auth/bot-config");

    /* a helper to manage users with different language settings, if your bot needs one */
    private static L10nHelper langBundle = new L10nHelper("lang/strings");

    /* a simple manager to track the user state in a conversation, if you bot needs one */
    private static StateTracker stateTracker = new StateTracker(State.getList());

    /* a helper to log events */
    private static Logger LOGGER = new Logger("log/log-messages");

    /* services your bot may use */
    /* TODO: use the service you actually implemented */
    private static PersistenceService persistenceService;

    /* ---------------------------------------------------- */

    public String getBotUsername() {
        return authBundle.getString("bot-username");
    }

    public String getBotToken() {
        return authBundle.getString("bot-token");
    }

    /* basically this is the only method your bot will call.
     * The design proposed below turns it into a simple switcher, just recognizing
     * the meaning of the update and delegating its handling to specific methods
     */
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

                } else {

                    /* do nothing... or do something else */
                    LOGGER.log("incoming_update", sender.getId().toString(), "message", "none");

                }

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

    /* ---------------------------------------------------- */

    /** commmand handlers **/

    private void onCommandStart(Update update) {

        Message incomingMessage = update.getMessage();
        User sender = incomingMessage.getFrom();

        LOGGER.log("incoming_update", sender.getId().toString(), "message", "onCommandStart");

        /* this is the bot response */
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

        LOGGER.log("incoming_update", sender.getId().toString(), "message", "onCommandHelp");

        /* this is the bot response */
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

        LOGGER.log("incoming_update", sender.getId().toString(), "message", "onCommandSettings");

        /* this is the bot response */
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

        LOGGER.log("incoming_update", sender.getId().toString(), "callback", "onCallbackFoo");

        /* this will edit the message from which the callback came from */
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                .setChatId(String.valueOf(sender.getId()))
                .setMessageId(originalMessage.getMessageId());

        /* this is the bot response */
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

        LOGGER.log("incoming_update", sender.getId().toString(), "message", "onInsertData");

        /* this is the bot response */
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

        LOGGER.log("incoming_update", sender.getId().toString(), "message", "onInsertOtherData");

        /* this is the bot response */
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
