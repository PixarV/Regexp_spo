package regexp;

import lombok.EqualsAndHashCode;
import nfa.State;

@EqualsAndHashCode
abstract public class RegExp {
    public abstract State createNFA();
}
