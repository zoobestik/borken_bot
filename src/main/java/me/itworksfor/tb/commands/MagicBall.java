package me.itworksfor.tb.commands;

import me.itworksfor.tb.lib.Command;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;

import static me.itworksfor.tb.lib.Utils.getRandomInRange;

public class MagicBall extends Command {
    protected static String commandIdentifier = "8ball";
    protected static String description = "description 8ball";

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

    protected String replay(AbsSender sender, User user, Chat chat, String message) {
        return getAnswers()[getRandomInRange(1, answers.length)];
    }

    @Override
    public boolean isValidAction(String action, Message message) {
        if (action.charAt(0) == '/') {
            action = action.substring(1);
        }
        return action.startsWith("8") || action.startsWith("ball") || action.startsWith("8ball");
    }
}
