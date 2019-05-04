package regexp;

import nfa.State;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class BaseTest {

    Base base = new Base('d');

    @Test
    public void createNFA() {
        State nfa = base.createNFA();
        State end = nfa.getTransitions().get(base);

        assertTrue(base.match('d'));
        assertFalse(base.match('a'));
        assertTrue(end.isEnd());
    }
}