package me.telegram.borken_bot.commands;

import me.telegram.borken_bot.GMBot;
import me.telegram.borken_bot.lib.Command;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Help extends Command {
    protected static String commandIdentifier = "help";
    protected static String description = "посмотреть список всех комманд";

    GMBot bot;

    public Help(GMBot userBot) {
        super(commandIdentifier, description);
        bot = userBot;
    }

    static protected String getCommandHelp(BotCommand command) {
        return String.format(
                "/%s - %s",
                command.getCommandIdentifier(),
                command.getDescription()
        );
    }

    static protected String getCommandHelp(Command command) {
        String text = getCommandHelp((BotCommand) command);
        String[] shortNotation = command.getShortNotations();

        if (shortNotation != null && shortNotation.length > 0) {
            text = String.format(
                    "\n%s\n%s",
                    text,
                    Arrays.stream(shortNotation)
                            .map(notation -> "/" + notation)
                            .collect(Collectors.joining(", "))
            );
        }

        return text;
    }

    @Override
    protected String replay(AbsSender sender, User user, Chat chat, String text) {
        return bot.getRegistry().stream()
                .map(Help::getCommandHelp)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        return action.startsWith("help");
    }
}
