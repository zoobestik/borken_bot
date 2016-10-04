package me.itworksfor.tb;

import me.itworksfor.tb.bot.Dice;
import org.telegram.telegrambots.TelegramBotsApi;

public class App {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Dice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
