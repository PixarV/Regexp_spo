package nfa;


import lombok.experimental.UtilityClass;
import regexp.Base;

import java.util.*;

@UtilityClass
public class MatchUtil {
    private void getFuckingStatesForState(State state, List<State> fuckingStates, Set<State> visited) {
        List<State> epsilons = state.getEpsilons();

        if (epsilons.size() == 0) {
            fuckingStates.add(state);
            return;
        }

        for (State epsilon : epsilons) {
            visited.add(state);
            getFuckingStatesForState(epsilon, fuckingStates, visited);
        }
    }

    private Optional<Base> getBase(Set<Base> bases, char c) {
        for (Base base : bases)
            if (base.match(c)) return Optional.of(base);
        return Optional.empty();
    }

    private boolean checkCurrentStates(List<State> currentStates) {
        for (State current : currentStates)
            if (current.isEnd()) return true;
        return false;
    }

    public boolean match(Pair<State, State> nfa, String word) {
        State start = nfa.getLeft();

        List<State> currentStates = new ArrayList<>();
        getFuckingStatesForState(start, currentStates, new HashSet<>());

        for (char c : word.toCharArray()) {
            List<State> nextStates = new ArrayList<>();

            for (State current : currentStates) {
                Set<Base> bases = current.getTransitions().keySet();

                Optional<Base> base = getBase(bases, c);
                if (base.isPresent()) {
                    State next = current.getTransitions().get(base.get());
                    getFuckingStatesForState(next, nextStates, new HashSet<>());
                }
            }

            currentStates = nextStates;
        }

        return checkCurrentStates(currentStates);
    }
}
