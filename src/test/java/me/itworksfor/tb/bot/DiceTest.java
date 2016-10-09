package me.itworksfor.tb.bot;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class DiceTest {
    Dice dice;

    @Before
    public void setUp() {
        dice = new Dice();
    }

    @Test
    public void getRandomInRange() throws Exception {
        IntStream.rangeClosed(1, 10).forEach(num -> {
            int oneOrTwo = Dice.getRandomInRange(1, 3);
            assertTrue(1 <= oneOrTwo || oneOrTwo <= 2);
        });
    }
}