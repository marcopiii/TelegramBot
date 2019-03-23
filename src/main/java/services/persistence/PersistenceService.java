package services.persistence;

import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Define all the persistence operations your application needs to perform,
 * then implement them using the API of the database service you chose.
 **/
public interface PersistenceService {

    void registerUser(User telegramUser);

    void blockUser(User telegramUser);

    /* TODO: add any method you need */

}
