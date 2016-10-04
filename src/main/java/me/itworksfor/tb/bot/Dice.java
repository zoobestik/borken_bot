package me.itworksfor.tb.bot;

import me.itworksfor.tb.lib.MessageAction;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Random;

import static me.itworksfor.tb.Constants.DICE;
import static me.itworksfor.tb.Constants.TIMER_BOT_NAME;
import static me.itworksfor.tb.Constants.TIMER_BOT_TOKEN;

public class Dice extends TelegramLongPollingBot {

    private static Random random = new Random();

    private static int getRandomInRange(int min, int max) {
        return random.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    public void onUpdateReceived(Update update) {
        MessageAction action = MessageAction.parse(update);

        if (action != null) {
            switch (action.getCommand()) {
                case DICE:
                    onDiceMessage(action.getText(), action.getMessage(), update);
                    break;
            }
        }
    }

    private void onDiceMessage(String text, Message message, Update update) {
        int min = 1;
        int max = 20;
        int val = getRandomInRange(min, max);

        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId().toString());
        sendMessageRequest.setText(String.valueOf(val));

        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return TIMER_BOT_NAME;
    }

    public String getBotToken() {
        return TIMER_BOT_TOKEN;
    }
}
