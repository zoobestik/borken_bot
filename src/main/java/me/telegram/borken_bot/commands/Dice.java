package me.telegram.borken_bot.commands;

import me.telegram.borken_bot.lib.Command;
import me.telegram.borken_bot.lib.Utils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static me.telegram.borken_bot.lib.Utils.getNumericTokenLength;

public class Dice extends Command {
    protected static String commandIdentifier = "dice";
    protected static String description = "бросить кубик с X-сторонами один или Y раз";

    public Dice() {
        super(commandIdentifier, description);
    }

    protected String replay(AbsSender sender, User user, Chat chat, String message) {
        Map<String, String> params = tokenize(message);

        if (params == null) {
            params = tokenize(message);
        }

        return getMessage(params);
    }

    public Map<String, String> tokenize(String message) {
        Map<String, String> groups = new HashMap<>();

        int i = message.indexOf(this.getCommandIdentifier());

        if (i != -1) {
            message = "d" + message.substring(this.getCommandIdentifier().length());
        }

        message = StringUtils.deleteWhitespace(message);

        /* ====== "min" parser (begin) ====== */
        i = getNumericTokenLength(message);

        if (i != 0) {
            groups.put("min", message.substring(0, i));
            message = message.substring(i);
        }
        /* ====== / "min" (end) */

        /* ====== "max" parser (begin) ====== */
        if (!"d".equals(message.substring(0, 1))) {
            return null;
        }

        message = message.substring(1);

        i = getNumericTokenLength(message);

        if (i != 0) {
            groups.put("max", message.substring(0, i));
            message = message.substring(i);
        }
         /* ====== / "max" (end) ====== */

        /* ====== "modifier" parser (begin) ====== */
        if (message.length() > 0 && "+".equals(message.substring(0, 1))) {
            message = message.substring(1);

            i = getNumericTokenLength(message);

            if (i != 0) {
                groups.put("modifier", message.substring(0, i));
            }
        }
        /* ====== / "modifier" (end) ======= */

        return groups;
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        return tokenize(action).size() > 0;
    }

    protected String getMessage(Map<String, String> params) {
        if (params != null) {
            int max = params.get("max") != null ? Integer.parseInt(params.get("max"), 10) : 20;
            int count = params.get("count") != null ? Integer.parseInt(params.get("count"), 10) : 1;

            return String.valueOf(IntStream.generate(() -> Utils.getRandomInRange(1, max))
                    .limit(count)
                    .sum());
        }
        return null;
    }

    public String[] getShortNotations() {
        return new String[]{"dice20", "d20", "2d4+3"};
    }
}
