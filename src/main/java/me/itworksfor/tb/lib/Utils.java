package me.itworksfor.tb.lib;

import java.util.Random;

public class Utils {
    private static Random random;

    protected static Random getRandom() {
        if (random == null) {
            random = new Random();
        }

        return random;
    }

    public static Integer getRandomInRange(Integer min, Integer max) {
        if (min.equals(max)) return min;

        if (min > max) {
            Integer val = min;
            min = max;
            max = val;
        }

        return getRandom().ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
}
