package me.itworksfor.tb.lib;

import org.junit.Ignore;
import org.junit.Test;

import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {
    @Test
    public void checkGetRandomInRange() {
        getRandomInRange(1, 2, actual -> {
            assertTrue(actual >= 1 && actual <= 2);
            return null;
        });

    }

    @Test @Ignore
    public void checkGetBigRandomInRange() {
        getRandomInRange(999999999, 2, 999999999, actual -> {
            assertTrue(actual >= 1 && actual <= 999999999);
            return null;
        });
    }

    @Test
    public void checkGetRandomInRangeConstant() {
        getRandomInRange(3, 3, actual -> {
            assertEquals(actual, new Integer(3));
            return null;
        });
    }

    @Test
    public void checkGetRandomInRangeReverse() {
        getRandomInRange(9, 8, actual -> {
            assertTrue(actual >= 8 && actual <= 9);
            return null;
        });
    }

    private void getRandomInRange(int min, int max, Function<Integer, Void> test) {
        getRandomInRange(1000, min, max, test);
    }

    private void getRandomInRange(int count, int min, int max, Function<Integer, Void> test) {
        IntStream.rangeClosed(0, count).forEach(i -> test.apply(Utils.getRandomInRange(min, max)));
    }
}