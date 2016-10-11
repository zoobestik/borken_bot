package me.itworksfor.tb;

import me.itworksfor.tb.commands.Dice;
import me.itworksfor.tb.commands.Help;
import me.itworksfor.tb.commands.MagicBall;
import me.itworksfor.tb.lib.Command;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Arrays;
import java.util.List;

public class GMBot extends TelegramLongPollingBot {
    protected final List<Command> registry;

    GMBot() {
        registry = Arrays.asList(
                new Help(this),
                new Dice(),
                new MagicBall()
        );
    }

    public List<Command> getRegistry() {
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
                        .ifPresent(command -> {
                            command.execute(this, message.getFrom(), message.getChat(), action.split("\\s+"));
                        });
            }
        }
    }

    @Override
    public String getBotUsername() {
        return Constants.TIMER_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constants.TIMER_BOT_TOKEN;
    }
}
