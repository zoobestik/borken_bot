package me.telegram.borken_bot.commands;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.commands.BotCommand;

abstract public class AbsCommand extends BotCommand {

    public AbsCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public abstract boolean isValidAction(String action, Message message);

    public String[] getShortNotations() {
        return null;
    }
}
