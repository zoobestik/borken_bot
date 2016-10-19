package me.telegram.borken_bot.commands;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DiceTest {
    @Test
    public void testShortTokenizer() throws Exception {
        Dice dice = new Dice();

        Map<String, String> groups0 = dice.tokenize("d20");

        assertEquals(null, groups0.get("min"));
        assertEquals("20", groups0.get("max"));
        assertEquals(null, groups0.get("modifier"));

        Map<String, String> groups1 = dice.tokenize("2d4");

        assertEquals("2", groups1.get("min"));
        assertEquals("4", groups1.get("max"));
        assertEquals(null, groups1.get("modifier"));

        Map<String, String> groups2 = dice.tokenize("d8+2");

        assertEquals(null, groups2.get("min"));
        assertEquals("8", groups2.get("max"));
        assertEquals("2", groups2.get("modifier"));

        Map<String, String> groups3 = dice.tokenize("3 d  \n6 + 5");

        assertEquals("3", groups3.get("min"));
        assertEquals("6", groups3.get("max"));
        assertEquals("5", groups3.get("modifier"));
    }

    @Test
    public void testLongTokenizer() throws Exception {
        Dice dice = new Dice();

        Map<String, String> groups0 = dice.tokenize("dice20");

        assertEquals(null, groups0.get("min"));
        assertEquals("20", groups0.get("max"));
        assertEquals(null, groups0.get("modifier"));

        Map<String, String> groups1 = dice.tokenize("dice20+1");

        assertEquals(null, groups1.get("min"));
        assertEquals("20", groups1.get("max"));
        assertEquals("1", groups1.get("modifier"));
    }
}