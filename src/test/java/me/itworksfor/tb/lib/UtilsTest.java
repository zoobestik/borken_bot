package me.itworksfor.tb.lib;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class UtilsTest {
    @Test
    public void checkGetRandomInRange() {
        getRandomInRange(1, 2, actual -> {
            assertTrue(actual >= 1 && actual <= 2);
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
        IntStream.rangeClosed(0, 100).forEach(i -> test.apply(Utils.getRandomInRange(min, max)));
    }
}