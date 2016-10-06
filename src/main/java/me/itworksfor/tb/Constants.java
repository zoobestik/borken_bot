package me.itworksfor.tb;

import javax.inject.Singleton;

@Singleton
public class Constants {
    public static final Boolean debug = true;
    public static final String pathToLogs = "./logs";
    public static final String dbLink = "jdbc:mysql://localhost:3307/tb?useUnicode=true&characterEncoding=UTF-8";
    public static final String dbDriver = "com.mysql.jdbc.Driver";
    public static final String dbUser = "test";
    public static final String dbPassword = "test";

    public static String TIMER_BOT_TOKEN = "";
    public static String TIMER_BOT_NAME = "";
}
