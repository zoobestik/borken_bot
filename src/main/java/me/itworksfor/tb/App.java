package me.itworksfor.tb;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.BotOptions;

public class App {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new GMBot(new BotOptions(), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
