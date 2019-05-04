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

        assertTrue(MatchUtil.match(nfa,""));
        assertTrue(MatchUtil.match(nfa,"d"));
        assertTrue(MatchUtil.match(nfa,"dd"));
        assertTrue(MatchUtil.match(nfa,"ddd"));
        assertFalse(MatchUtil.match(nfa,"dda"));
    }

}