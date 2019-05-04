package nfa;

import org.junit.Before;
import org.junit.Test;
import parser.RegexpParser;
import regexp.RegExp;

import static org.junit.Assert.*;

public class MatchUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void matchForSimpleFactor() {
        RegexpParser regexpParser = new RegexpParser("d*", 0);
        RegExp regex = regexpParser.regex();
        Pair<State, State> nfa = regex.createNFA();

        assertTrue(MatchUtil.match(nfa, ""));
        assertTrue(MatchUtil.match(nfa, "d"));
        assertTrue(MatchUtil.match(nfa, "dd"));
        assertTrue(MatchUtil.match(nfa, "ddd"));
        assertFalse(MatchUtil.match(nfa, "dda"));
    }

    @Test
    public void matchForSimpleConcat() {
        RegexpParser regexpParser = new RegexpParser("abc", 0);
        RegExp regex = regexpParser.regex();
        Pair<State, State> nfa = regex.createNFA();

        assertTrue(MatchUtil.match(nfa, "abc"));
        assertFalse(MatchUtil.match(nfa, "ab"));
        assertFalse(MatchUtil.match(nfa, "abcc"));
        assertFalse(MatchUtil.match(nfa, ""));
    }

    @Test
    public void matchForConcatFactor() {
        RegexpParser regexpParser = new RegexpParser("ab*c*", 0);
        RegExp regex = regexpParser.regex();
        Pair<State, State> nfa = regex.createNFA();

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
        RegexpParser regexpParser = new RegexpParser("(a|b)*d", 0);
        RegExp regex = regexpParser.regex();
        Pair<State, State> nfa = regex.createNFA();

        assertTrue(MatchUtil.match(nfa, "d"));
        assertTrue(MatchUtil.match(nfa, "ad"));
        assertTrue(MatchUtil.match(nfa, "bd"));
        assertTrue(MatchUtil.match(nfa, "abbd"));
        assertFalse(MatchUtil.match(nfa, "ab"));
    }
}