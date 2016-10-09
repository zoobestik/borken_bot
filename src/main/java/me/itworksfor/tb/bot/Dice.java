package me.itworksfor.tb.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.itworksfor.tb.Constants.TIMER_BOT_NAME;
import static me.itworksfor.tb.Constants.TIMER_BOT_TOKEN;

public class Dice extends TelegramLongPollingBot {
    private static Pattern trimPattern = Pattern.compile("\\s");
    private static Pattern dicePattern = Pattern.compile("^d(?<num>\\d+)$");
    private static Random random = new Random();

    protected static int getRandomInRange(int min, int max) {
        return random.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    protected String clean(String text) {
        return trimPattern.matcher(text).replaceAll("");
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                Matcher matcher = dicePattern.matcher(clean(message.getText()));

                if (matcher.find() && matcher.group("num") != null) {
                    onDiceMessage(message, Integer.parseInt(matcher.group("num"), 10));
                }
            }
        }
    }

    private void onDiceMessage(Message message, Integer max) {
        onDiceMessage(message, 1, max);
    }

    private void onDiceMessage(Message message, Integer min, Integer max) {
        Integer val = getRandomInRange(min, max);

        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId().toString());
        sendMessageRequest.setText(val.toString());

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
