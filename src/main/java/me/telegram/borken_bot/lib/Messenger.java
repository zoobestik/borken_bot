package me.telegram.borken_bot.lib;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.function.Function;

public class Messenger {
    public static void replay(AbsSender sender, Chat chat, Function<SendMessage, String> getText) {
        SendMessage messageRequest = new SendMessage();

        String text = getText.apply(messageRequest);

        if (text != null) {
            messageRequest.setChatId(chat.getId().toString());
            messageRequest.setText(text);

            try {
                sender.sendMessage(messageRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
