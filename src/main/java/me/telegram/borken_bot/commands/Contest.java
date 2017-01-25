package me.telegram.borken_bot.commands;

import me.telegram.borken_bot.lib.Messenger;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

public class Contest extends AbsCommand {
    protected static String commandIdentifier = "contest";
    protected static String description = "...";

    public Contest() {
        super(commandIdentifier, description);
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        return action.equals("contest");
    }

    public String[] getShortNotations() {
        return new String[]{"cn", "cn3"};
    }

    @Override
    public void execute(AbsSender sender, User user, Chat chat, String[] args) {
        String message = String.join("", args);
        Messenger.replay(sender, chat, request -> replayMessage(message));
    }

    protected String replayMessage(String message) {
        ContestRequest params = tokenize(message);

        // ...

        return null;
    }

    protected ContestRequest tokenize(String message) {
        return null;
    }

    protected class ContestRequest {
    }
}
