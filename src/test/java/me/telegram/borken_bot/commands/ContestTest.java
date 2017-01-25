package me.telegram.borken_bot.commands;

import lib.TestUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestTest {
    private Contest contest;

    @Before
    public void setUp() {
        contest = new Contest();
    }

    @Test
    public void testIsValidAction() {
        assertTrue(contest.isValidAction("contest", null));
        assertFalse(contest.isValidAction("contest_", null));
        assertFalse(contest.isValidAction("_contest", null));
    }

    @Test
    public void testGetShortNotations() {
        String[] notations = contest.getShortNotations();

        assertEquals(2, notations.length);
        assertEquals("cn", notations[0]);
        assertEquals("cn3", notations[1]);
    }

    @Test
    public void testGetMessageWithNoUsers() {
        assertEquals("Command should contains user names", contest.replayMessage("test string"));
    }

    @Test
    public void testGetMessageWithOneUsers() {
        assertEquals("Command should contains more then @user", contest.replayMessage("@user"));
    }

    @Test
    public void testGetMessageWithUndefinedUsers() {
        assertEquals(" @notUser not in chat", contest.replayMessage("@user @notUser"));
    }


    @Test
    public void testGetMessageWithContestStart() {
        assertEquals("2", contest.replayMessage("@user1 @user2"));
    }

    @Test
    public void testConflictCommands() {
        assertFalse(TestUtils.isConflict(contest));
    }
}
