package me.itworksfor.tb.commands;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.itworksfor.tb.lib.Utils.getRandomInRange;

public class DiceReply extends ReplayCommand {
    private static Pattern trimPattern = Pattern.compile("\\s");
    private static Pattern dicePattern = Pattern.compile("^d(?<num>\\d+)$");

    protected String clean(String text) {
        return trimPattern.matcher(text).replaceAll("");
    }

    protected String getMessage(Integer max) {
        return getMessage(1, max);
    }

    protected String getMessage(Integer min, Integer max) {
        return getRandomInRange(min, max).toString();
    }

    public String replay(Message message, Update update) {
        Matcher matcher = dicePattern.matcher(clean(message.getText()));

        if (matcher.find() && matcher.group("num") != null) {
            return getMessage(Integer.parseInt(matcher.group("num"), 10));
        }

        return null;
    }
}
