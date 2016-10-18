package me.telegram.borken_bot.lib;

import java.util.SplittableRandom;

public class Utils {
    private static SplittableRandom random;

    protected static SplittableRandom getRandom() {
        if (random == null) {
            random = new SplittableRandom();
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

        return getRandom().nextInt(min, max + 1);
    }
}
