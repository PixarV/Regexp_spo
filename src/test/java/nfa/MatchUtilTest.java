package nfa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void matchForSimpleFactor() {
        Pair<State, State> nfa = MatchUtil.compileRegExp("d*");

        assertTrue(MatchUtil.match(nfa, ""));
        assertTrue(MatchUtil.match(nfa, "d"));
        assertTrue(MatchUtil.match(nfa, "dd"));
        assertTrue(MatchUtil.match(nfa, "ddd"));
        assertFalse(MatchUtil.match(nfa, "dda"));
    }

    @Test
    public void matchForSimpleConcat() {
        Pair<State, State> nfa = MatchUtil.compileRegExp("abc");

        assertTrue(MatchUtil.match(nfa, "abc"));
        assertFalse(MatchUtil.match(nfa, "ab"));
        assertFalse(MatchUtil.match(nfa, "abcc"));
        assertFalse(MatchUtil.match(nfa, ""));
    }

    @Test
    public void matchForConcatFactor() {
        Pair<State, State> nfa = MatchUtil.compileRegExp("ab*c*");

        assertTrue(MatchUtil.match(nfa, "ac"));
        assertTrue(MatchUtil.match(nfa, "abc"));
        assertTrue(MatchUtil.match(nfa, "abbc"));
        assertTrue(MatchUtil.match(nfa, "ab"));
        assertTrue(MatchUtil.match(nfa, "abcc"));
        assertFalse(MatchUtil.match(nfa, ""));
        assertFalse(MatchUtil.match(nfa, "bc"));
    }

    @Test
    public void matchForUnion() {
        Pair<State, State> nfa = MatchUtil.compileRegExp("(a|b)*d");

        assertTrue(MatchUtil.match(nfa, "d"));
        assertTrue(MatchUtil.match(nfa, "ad"));
        assertTrue(MatchUtil.match(nfa, "bd"));
        assertTrue(MatchUtil.match(nfa, "abbd"));
        assertFalse(MatchUtil.match(nfa, "ab"));
    }

    @Test
    public void matchForHardCase() { // %3 = 0
        Pair<State, State> nfa = MatchUtil.compileRegExp("(0|(1(01*0)*1))*");

        assertTrue(MatchUtil.match(nfa, ""));
        assertTrue(MatchUtil.match(nfa, "0"));
        assertTrue(MatchUtil.match(nfa, "000"));
        assertTrue(MatchUtil.match(nfa, "11"));
        assertTrue(MatchUtil.match(nfa, "1001"));
        assertTrue(MatchUtil.match(nfa, "1100"));
        assertTrue(MatchUtil.match(nfa, "1100000"));
        assertFalse(MatchUtil.match(nfa, "1"));
        assertFalse(MatchUtil.match(nfa, "101"));
        assertFalse(MatchUtil.match(nfa, "10111"));
    }

    @Test
    public void matchForPlus() {
        Pair<State, State> nfa = MatchUtil.compileRegExp("ab+c*d");

        assertTrue(MatchUtil.match(nfa, "abd"));
        assertTrue(MatchUtil.match(nfa, "abbcd"));
        assertTrue(MatchUtil.match(nfa, "abbbccd"));
        assertFalse(MatchUtil.match(nfa, "ad"));
        assertFalse(MatchUtil.match(nfa, "acd"));
    }
}