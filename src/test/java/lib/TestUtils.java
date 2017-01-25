package lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.telegram.borken_bot.GMBot;
import me.telegram.borken_bot.commands.AbsCommand;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

public class TestUtils {
    public static void executeSimple(AbsSender sender, AbsCommand command, String[] args) throws IOException {
        String chatJson = "{" +
                "\"id\": \"123\"," +
                "\"type\", \"private\"" +
        "}";

        ObjectMapper mapper = new ObjectMapper();
        Chat chat = mapper.readValue(chatJson, Chat.class);
        command.execute(sender, new User(), chat, args);
    }

    public static boolean isConflict(AbsCommand command) {
        return isConflict(mock(GMBot.class), command);
    }

    public static boolean isConflict(GMBot bot, AbsCommand command) {
        return hasCommand(
                bot,
                action -> command.isValidAction(action, new Message()),
                item -> !command.getClass().isInstance(item)
        );
    }

    protected static boolean hasCommand(GMBot bot, Function<String, Boolean> test, Function<AbsCommand, Boolean> filter) {
        return GMBot.getCommandsList(bot)
                .parallelStream()
                .filter(filter::apply)
                .flatMap(TestUtils::extractCommands)
                .anyMatch(test::apply);
    }

    protected static Stream<? extends String> extractCommands(AbsCommand command) {
        return new ArrayList<String>() {
            {
                add(command.getCommandIdentifier());
                String[] shortNotations = command.getShortNotations();
                if (shortNotations != null) {
                    Collections.addAll(this, shortNotations);
                }
            }
        }.stream();
    }
}
