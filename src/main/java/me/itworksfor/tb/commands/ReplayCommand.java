package me.itworksfor.tb.commands;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

abstract public class ReplayCommand {
    abstract public String replay(Message message, Update update);
}
