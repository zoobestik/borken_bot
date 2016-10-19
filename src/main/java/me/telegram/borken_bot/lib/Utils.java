package me.telegram.borken_bot.lib;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.SplittableRandom;

public class Utils {
    private static SplittableRandom random;

    @NotNull
    protected static SplittableRandom getRandom() {
        if (random == null) {
            random = new SplittableRandom();
        }

        return random;
    }

    @NotNull
    public static Integer getRandomInRange(Integer min, Integer max) {
        if (min.equals(max)) return min;

        if (min > max) {
            Integer val = min;
            min = max;
            max = val;
        }

        return getRandom().nextInt(min, max + 1);
    }

    @NotNull
    public static int getNumericTokenLength(String str) {
        int i = 0;

        if (str != null) {
            int length = str.length();

            while (i < length) {
                if (!StringUtils.isNumeric(str.substring(i, i + 1))) {
                    break;
                }
                i++;
            }
        }

        return i;
    }
}
