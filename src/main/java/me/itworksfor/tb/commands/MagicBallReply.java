package me.itworksfor.tb.commands;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import static me.itworksfor.tb.lib.Utils.getRandomInRange;

public class MagicBallReply extends ReplayCommand {
    protected String[] answers;

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

    private String getAnswerText() {
        return getAnswers()[getRandomInRange(1, answers.length)];
    }

    @Override
    public String replay(Message message, Update update) {
        if (message.getText().trim().startsWith("(8)")) {
            return getAnswerText();
        }

        return null;
    }
}
