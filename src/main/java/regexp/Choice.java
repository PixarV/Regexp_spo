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
        State start = new State(false);
        State end = new State(true);
        Pair<State, State> nfa1 = one.createNFA();
        Pair<State, State> nfa2 = two.createNFA();

        start.addEpsilonTransition(nfa1.getLeft());
        start.addEpsilonTransition(nfa2.getLeft());
        nfa1.getRight().addEpsilonTransition(end);
        nfa2.getRight().addEpsilonTransition(end);

        nfa1.getRight().setEnd(false);
        nfa2.getRight().setEnd(false);

        return new Pair<>(start, end);
    }
}
