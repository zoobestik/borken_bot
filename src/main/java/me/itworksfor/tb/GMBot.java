package me.itworksfor.tb;

import me.itworksfor.tb.commands.DiceReply;
import me.itworksfor.tb.commands.MagicBallReply;
import me.itworksfor.tb.commands.ReplayCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Arrays;

public class GMBot extends TelegramLongPollingCommandBot {
    protected ReplayCommand[] replays;

    GMBot() {
        replays = new ReplayCommand[]{
                new DiceReply(),
                new MagicBallReply()
        };
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Arrays.stream(replays).anyMatch(cmd -> replyCommandText(update, cmd));
    }

    protected boolean replyCommandText(Update update, ReplayCommand command) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                String text = command.replay(message, update);

                if (text != null) {
                    SendMessage sendMessageRequest = new SendMessage();
                    sendMessageRequest.setChatId(message.getChatId().toString());
                    sendMessageRequest.setText(text);

                    try {
                        sendMessage(sendMessageRequest);
                        return true;
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
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
