package me.itworksfor.tb.commands;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.itworksfor.tb.lib.Utils.getRandomInRange;

public class DiceReply extends ReplayCommand {
    private static Pattern trimPattern = Pattern.compile("\\s");
    private static Pattern dicePattern = Pattern.compile("^(?<min>\\d+)?d(?<max>\\d+)$");

    protected String clean(String text) {
        return trimPattern.matcher(text).replaceAll("");
    }

    protected String getMessage(int min, int max) {
        return getRandomInRange(min, max).toString();
    }

    public String replay(Message message, Update update) {
        Matcher matcher = dicePattern.matcher(clean(message.getText()));

        if (matcher.find() && matcher.group("max") != null) {
            int max = Integer.parseInt(matcher.group("max"), 10);
            int min = matcher.group("min") != null ? Integer.parseInt(matcher.group("min"), 10) : 1;
            return getMessage(min, max);
        }

        return null;
    }
}
