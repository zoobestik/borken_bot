package me.telegram.borken_bot.lib;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

abstract public class Command extends BotCommand {

    public Command(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public void execute(AbsSender sender, User user, Chat chat, String[] args) {
        String text = replay(sender, user, chat, String.join(" ", args));

        if (text != null) {
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(chat.getId().toString());
            sendMessageRequest.setText(text);

            try {
                sender.sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract String replay(AbsSender sender, User user, Chat chat, String text);

    public abstract boolean isValidAction(String action, Message message);

    public String[] getShortNotations() {
        return null;
    }

    ;
}
