package me.itworksfor.tb;

import org.telegram.telegrambots.TelegramBotsApi;

public class App {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new GMBot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
