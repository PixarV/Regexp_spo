package regexp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import nfa.State;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Sequence extends RegExp {
    List<RegExp> factors = new LinkedList<>();

    public Sequence(RegExp... regExps) {
        factors.addAll(Arrays.asList(regExps));
    }

    public void addRegEx(RegExp e) {
        factors.add(e);
    }

    public int getSize() {
        return factors.size();
    }

    public RegExp getFirst()  {
        return factors.get(0);
    }

    @Override
    public State createNFA() { //concat
        // change isEnd
//        Optional<Pair<State, State>> reduce = factors.stream()
//                .map(State::fromSymbol)
//                .reduce((s1, s2) -> {
//                    s1.getRight().addEpsilonTransition(s2.getLeft());
//                    return new Pair<>(s1.getLeft(), s2.getRight());
//                });
        return null;
    }
}
