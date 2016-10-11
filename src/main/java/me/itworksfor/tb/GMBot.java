package me.itworksfor.tb;

import me.itworksfor.tb.commands.Dice;
import me.itworksfor.tb.commands.MagicBall;
import me.itworksfor.tb.lib.Command;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Arrays;
import java.util.List;

public class GMBot extends TelegramLongPollingBot {
    protected List<Command> registry;

    GMBot() {
        registry = Arrays.asList(
                new Dice(),
                new MagicBall()
        );
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.isCommand()) {
                String actionText = message.getText().trim();

                registry.stream()
                        .filter(command -> command.isValidAction(actionText, message))
                        .findAny()
                        .ifPresent(command -> {
                            command.execute(this, message.getFrom(), message.getChat(), actionText.split("\\s+"));
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
