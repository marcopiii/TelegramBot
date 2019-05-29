# TelegramBot

*A project template to create a clean, scalable and easy Telegram Bot with Java.*

##### Introduction
During my experiments with Telegram bots I often had to write the same project structure again and again. Therefore I found quite useful to create a custom general template.
Clearly this is just one of the way you can organize your project and of course it reflects my personal style of coding, however I think this may be helpful as an example for who is going to start playing with bots.
Anyway before using this template I suggest to read [this really good guide](https://monsterdeveloper.gitbooks.io/writing-telegram-bots-on-java/content) to quickly learn the very basic concept about writing a Telegram bot in java and always refer to the official [TelegramBot APIs](https://core.telegram.org/bots/api) official documentation.

### How to use this template?

* **IntelliJ IDEA**: download the project, open it, *Tools* > *Save project as template*. When you create a new project you will find this template in the *user defined* template menu.

### Dependencies

Some dependencies are already defined in the `pom.xml`

The main and essential dependency of this project is the excellent rubenlagus' [TelegramBots library](https://github.com/rubenlagus/TelegramBots) that easily permits to communicate with [Telegram Bot API](https://core.telegram.org/bots/api).
```
<dependency>
    <groupId>org.telegram</groupId>
    <artifactId>telegrambots</artifactId>
    <version>4.1.2</version>
</dependency>
```
If you want your bot to use emojis you will also find very useful vdurmont's [Emoji Java library](https://github.com/vdurmont/emoji-java).

```
<dependency>
    <groupId>com.vdurmont</groupId>
    <artifactId>emoji-java</artifactId>
    <version>4.0.0</version>
</dependency>
```
If you want your bot to periodically execute operations not fired by updates you can use Quartz Scheduler.
```
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.3.0</version>
</dependency>
```

## How does it work?

### Launcher
This executable class is what will actually deploy your bot and make it fetch for updates. Every time an update is detected the bot's function `onUpdateReceived(Update)`. If your bot also has to periodically execute some operation the Launcher can instantiate jobs, triggers and a scheduler (see Quartz documentation for details).

### bot.YourBot
In this project your bot will be a `TelegramLongPollingBot`. As said above it will "just" fetch for updates, and trigger `onUpdateReceived(Update)` on response. All you have to do is to actually implement any logic you want inside this function.
To keep it easily readable, maintainable and scalable the design proposed by this template turns this function in a simple switcher to detect commands, callbacks, plain text and delegate the handling to case-specific functions.
You can also define operations not related to incoming updates, to be called by jobs periodically triggered using Quartz library.

### Utils

#### StateTracker
You may want your bot to be able to have a conversation with the user, something like

<p align="center">
    <img src="https://i.ibb.co/tZCnfyD/Schermata-2019-05-18-alle-15-44-47.png" alt="example" width="500"/>
</p>

but since every message is received by your bot as an independent update, how can it recognize if the next plain text message has to be interpreted as a note title or a note body? An easy solution to bind those independent updates is to *move* the user through a series of states.
After receiving the `/addNote` command the bot can associate the sender to the state `GETTING_TITLE` and the next plain text message received from that user will be interpreted as the title. Then he can be moved to the next step, for example `GETTING_TEXT`, and so on.

#### L10nHelper
If you want your bot to speak multiple languages you will need to define multiple `.properties` files and instantiate a different ResourceBundle for each one. This utility is nothing more than a little shortcut to handle all those objects with a single structure in a less verbose way.

#### Logger
If you want to keep full track of all the activity of your bot once deployed you can use one of the many existing logging frameworks (and I suggest you to), but if you just want to run your bot on your local to test it or debug and you just need real time logging of major events the standard output will be enough. With this utility you will be able to do it with a short and clean syntax.

### Services

#### PersistenceService
You may want your bot to persist data, for example a list of users or user settings like the selected language. The options to achieve it are countless. Separating interface and implementation is always a good practice.

### Commons
Centralization in (almost) always a good choice. In this package you can keep all the constants used in your project.
* **Command**: The commands your bot should handle.
* **Callback**: If your bot exposes inline keyboards you can put here the callbacks' metadata keys that your bot should handle.
* **Lang**: If your bot supports multiple languages you should store here the language IDs.
* **State**: If your bot uses StateTracker here you can put states identifiers.
* **DBKeys**: If your bot needs to store data in a database you can store here the database keys.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **aSlug** - *Initial work* - [aSlug](https://github.com/aSlug)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

