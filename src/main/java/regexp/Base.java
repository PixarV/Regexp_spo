package regexp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import nfa.Pair;
import nfa.State;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Base extends RegExp {
    char c;
    // TODO symbol class

    @Override
    public State createNFA() {
        Pair<State, State> pair = State.fromSymbol(this);
        return pair.getLeft();
    }

    public boolean match(char symbol) {
        return c == symbol;
    }
}
