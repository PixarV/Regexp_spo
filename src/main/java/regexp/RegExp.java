package regexp;

import lombok.EqualsAndHashCode;
import nfa.Pair;
import nfa.State;

@EqualsAndHashCode
abstract public class RegExp {
    public abstract Pair<State, State> createNFA();
}
