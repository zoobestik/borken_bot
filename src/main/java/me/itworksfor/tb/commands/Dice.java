package me.itworksfor.tb.commands;

import me.itworksfor.tb.lib.Command;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.itworksfor.tb.lib.Utils.getRandomInRange;

public class Dice extends Command {
    protected static String commandIdentifier = "dice";
    protected static String description = "description dice";

    protected static Pattern trimPattern = Pattern.compile("\\s");
    protected static Pattern shortDicePattern = Pattern.compile("^/?(?<min>\\d+)?d(?<max>\\d+)$");

    public Dice() {
        super(commandIdentifier, description);
    }

    protected String clean(String text) {
        return trimPattern.matcher(text).replaceAll("");
    }

    protected String getMessage(int min, int max) {
        return getRandomInRange(min, max).toString();
    }

    protected String replay(AbsSender sender, User user, Chat chat, String message) {
        Matcher matcher = getShortMatcher(message);

        if (matcher.find() && matcher.group("max") != null) {
            int max = Integer.parseInt(matcher.group("max"), 10);
            int min = matcher.group("min") != null ? Integer.parseInt(matcher.group("min"), 10) : 1;
            return getMessage(min, max);
        }

        return null;
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        return getShortMatcher(action).matches();
    }

    protected Matcher getShortMatcher(String action) {
        return shortDicePattern.matcher(clean(action));
    }
}
