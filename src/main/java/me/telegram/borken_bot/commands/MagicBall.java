package me.telegram.borken_bot.commands;

import me.telegram.borken_bot.lib.Messenger;
import me.telegram.borken_bot.lib.Utils;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

public class MagicBall extends AbsCommand {
    protected static String commandIdentifier = "8ball";
    protected static String description = "спросить всевидящий шар";

    protected String[] answers;

    public MagicBall() {
        super(commandIdentifier, description);
    }

    String[] getAnswers() {
        if (answers == null) {
            answers = new String[]{
                    "Бесспорно",
                    "Это предрешено",
                    "Никаких сомнений",
                    "Определенно – «да»",
                    "Можешь быть уверен в этом",

                    "Мне кажется - «да»",
                    "Вероятнее всего",
                    "Хорошие перспективы",
                    "Да",
                    "Знаки говорят - да",

                    "Пока не ясно, попробуй еще раз",
                    "Спроси позже",
                    "Лучше не рассказывать тебе это сейчас",
                    "Сейчас нельзя предсказать",
                    "Сконцентрируйся и спроси снова",

                    "Даже не думай",
                    "Мой ответ - нет",
                    "Знаки говорят - нет",
                    "Перспективы не очень хорошие",
                    "Весьма сомнительно",
            };
        }

        return answers;
    }

    public void execute(AbsSender sender, User user, Chat chat, String[] args) {
        Messenger.replay(sender, chat, request -> getAnswers()[Utils.getRandomInRange(1, answers.length)]);
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        return action.startsWith("8") || action.startsWith("ball") || action.startsWith("8ball");
    }

    public String[] getShortNotations() {
        return new String[]{"8", "ball"};
    }
}
