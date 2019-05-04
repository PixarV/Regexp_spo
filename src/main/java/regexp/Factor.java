package regexp;

import enums.Quantifier;
import lombok.*;
import lombok.experimental.FieldDefaults;
import nfa.Pair;
import nfa.State;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE)
public class Factor extends RegExp {

    RegExp base;
    @Setter
    Quantifier quantifier;

    @Override
    public Pair<State, State> createNFA() { // closure
        State start = new State(false);
        State end = new State(true);
        Pair<State, State> nfa = base.createNFA();

        switch (quantifier) {
            case STAR:
                start.addEpsilonTransition(end);
                start.addEpsilonTransition(nfa.getLeft());
                nfa.getRight().addEpsilonTransition(end);
                nfa.getRight().addEpsilonTransition(nfa.getLeft());
                nfa.getRight().setEnd(false);
                return new Pair<>(start, end);

            case PLUS:
                start.addEpsilonTransition(nfa.getLeft());
                nfa.getRight().addEpsilonTransition(end);
                nfa.getRight().addEpsilonTransition(nfa.getLeft());
                nfa.getRight().setEnd(false);
                return new Pair<>(start, end);

            case QUESTION:
                start.addEpsilonTransition(end);
                start.addEpsilonTransition(nfa.getLeft());
                nfa.getRight().addEpsilonTransition(end);
                nfa.getRight().setEnd(false);
                return new Pair<>(start, end);

            case NONE:
                start.addEpsilonTransition(nfa.getLeft());
                nfa.getRight().addEpsilonTransition(end);
                nfa.getRight().setEnd(false);
                return new Pair<>(start, end);
        }
        return null;
    }


}
