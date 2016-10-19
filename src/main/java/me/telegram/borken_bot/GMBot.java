package me.telegram.borken_bot;

import me.telegram.borken_bot.commands.AbsCommand;
import me.telegram.borken_bot.commands.Dice;
import me.telegram.borken_bot.commands.Help;
import me.telegram.borken_bot.commands.MagicBall;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class GMBot extends TelegramLongPollingBot {
    protected final List<AbsCommand> registry;
    protected final Pattern whiteSpaces = Pattern.compile("\\s+");

    GMBot() {
        registry = Arrays.asList(
                new Help(this),
                new Dice(),
                new MagicBall()
        );
    }

    public List<AbsCommand> getRegistry() {
        return registry;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText().trim();
                final String action = text.charAt(0) == '/' ? text.substring(1) : text;

                registry.stream()
                        .filter(command -> command.isValidAction(action, message))
                        .findAny()
                        .ifPresent(command -> command.execute(this, message.getFrom(), message.getChat(), whiteSpaces.split(action)));
            }
        }
    }

    @Override
    public String getBotUsername() {
        return Constants.getConstant("TELEGRAM_BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return Constants.getConstant("TELEGRAM_BOT_TOKEN");
    }
}
