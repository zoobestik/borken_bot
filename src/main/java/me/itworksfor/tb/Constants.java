package me.itworksfor.tb;

import javax.inject.Singleton;

@Singleton
public class Constants {
    public static String getConstant(String name) {
        return System.getenv(name);
    }
}
