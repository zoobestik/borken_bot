package me.itworksfor.tb.commands;

import me.itworksfor.tb.lib.Command;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static me.itworksfor.tb.lib.Utils.getRandomInRange;

public class Dice extends Command {
    protected static String commandIdentifier = "dice";
    protected static String description = "бросить кубик с X-сторонами один или Y раз";

    protected Pattern longMaxPattern = Pattern.compile("^\\d+");
    protected Predicate<String> longCountPredicate = Pattern.compile("\\d+").asPredicate();
    protected String longSplitArgsPattern = "\\s+";

    protected static Pattern trimPattern = Pattern.compile("\\s");
    protected static Pattern shortDicePattern = Pattern.compile("^(?<min>\\d+)?d(?<max>\\d+)$", Pattern.CASE_INSENSITIVE);

    public Dice() {
        super(commandIdentifier, description);
    }

    protected String clean(String text) {
        return trimPattern.matcher(text).replaceAll("");
    }

    protected String replay(AbsSender sender, User user, Chat chat, String message) {
        Map<String, String> params = getMatchLongParams(message);

        if (params == null) {
            params = getMatchShortParams(message);
        }

        return getMessage(params);
    }

    protected Map<String, String> getMatchLongParams(String message) {
        if (message.startsWith(getCommandIdentifier())) {
            Map<String, String> params = new HashMap<>();

            message = message.substring(getCommandIdentifier().length());

            Matcher matcher = longMaxPattern.matcher(message);

            if (matcher.find()) {
                params.put("max", matcher.group());
                message = message.substring(matcher.group().length());
            }

            Arrays.stream(message.split(longSplitArgsPattern))
                    .filter(chunk -> (! "".equals(chunk) && longCountPredicate.test(chunk)))
                    .findFirst()
                    .ifPresent(count -> {
                        params.put("count", count);
                    });

            return params;
        }
        return null;
    }

    protected Map<String, String> getMatchShortParams(String message) {
        Matcher matcher = getShortMatcher(message);

        if (matcher.find()) {
            Map<String, String> params = new HashMap<>();

            params.put("max", matcher.group("max"));
            params.put("count", matcher.group("min"));

            return params;
        }
        return null;
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        return getMatchLongParams(action) != null || getMatchShortParams(action) != null;
    }

    protected String getMessage(Map<String, String> params) {
        if (params != null) {
            int max = params.get("max") != null ? Integer.parseInt(params.get("max"), 10) : 20;
            int count = params.get("count") != null ? Integer.parseInt(params.get("count"), 10) : 1;

            return String.valueOf(IntStream.generate(() -> getRandomInRange(1, max))
                    .limit(count)
                    .sum());
        }
        return null;
    }

    protected Matcher getShortMatcher(String action) {
        return shortDicePattern.matcher(clean(action));
    }

    public String[] getShortNotations() {
        return new String[]{"dice20", "d20", "2d3"};
    }
}
