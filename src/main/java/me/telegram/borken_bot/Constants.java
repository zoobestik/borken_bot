package me.telegram.borken_bot;

import javax.inject.Singleton;

@Singleton
public class Constants {
    public static String getConstant(String name) {
        return System.getenv(name);
    }
}
