package nfa;


import lombok.experimental.UtilityClass;
import parser.RegexpParser;
import regexp.Base;
import regexp.RegExp;

import java.util.*;

@UtilityClass
public class MatchUtil {

    public Pair<State, State> compileRegExp(String matcher) {
        RegexpParser regexpParser = new RegexpParser(matcher, 0);
        RegExp regexp = regexpParser.regexp();
        return regexp.createNFA();
    }

    public boolean match(Pair<State, State> nfa, String word) {
        State start = nfa.getLeft();

        List<State> currentStates = new ArrayList<>(); // states that you're located now
        getFuckingStatesForState(start, currentStates, new HashSet<>());

        for (char c : word.toCharArray()) {
            List<State> nextStates = new ArrayList<>(); // states where you can go

            for (State current : currentStates) {
                Map<Base, State> transitions = current.getTransitions();
                Set<Base> bases = transitions.keySet();

                Optional<Base> base = getBase(bases, c);
                if (base.isPresent()) {
                    State next = transitions.get(base.get());
                    getFuckingStatesForState(next, nextStates, new HashSet<>());
                }
            }

            currentStates = nextStates;
        }

        return checkEndInCurrentStates(currentStates);
    }

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

    private boolean checkEndInCurrentStates(List<State> currentStates) {
        for (State current : currentStates)
            if (current.isEnd()) return true;
        return false;
    }
}
