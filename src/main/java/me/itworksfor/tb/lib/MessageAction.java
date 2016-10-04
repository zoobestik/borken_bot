package me.itworksfor.tb.lib;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageAction {
    private static Pattern commandPattern = Pattern.compile("^/(?<command>[a-z][a-z0-9]+)( (?<text>.+))?");

    protected String command = null;
    protected Message message = null;
    protected String text = null;

    public static MessageAction parse(Message message) {
        if (message.hasText()) {
            Matcher matcher = commandPattern.matcher(message.getText().trim());

            if (matcher.find()) {
                MessageAction action = new MessageAction();

                action.command = matcher.group("command");
                action.text = matcher.group("text");
                action.message = message;

                return action;
            }
        }

        return null;
    }

    public static MessageAction parse(Update update) {
        if (update.hasMessage()) {
            return parse(update.getMessage());
        }

        return null;
    }

    public String getCommand() {
        return command;
    }

    public Message getMessage() {
        return message;
    }

    public String getText() {
        return text;
    }
}
