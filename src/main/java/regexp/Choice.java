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
public class Choice extends RegExp {
    RegExp one;
    RegExp two;

    @Override
    public Pair<State, State> createNFA() { //union
        return null;
    }
}
