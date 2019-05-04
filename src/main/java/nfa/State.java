package nfa;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import regexp.RegExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level=PRIVATE)
public class State {
    boolean isEnd;
    List<State> epsilons = new ArrayList();
    Map<RegExp, State> transitions = new HashMap<>();

    public State(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public void addEpsilonTransition(State to) {
        this.epsilons.add(to);
    }

    public void addTransition(State to, RegExp symbol) {
        this.transitions.put(symbol, to);
    }

    public static Pair<State, State> fromEpsilon() {
        State start = new State(false);
        State end = new State(true);
        start.addEpsilonTransition(end);
        return new Pair<>(start, end);
    }

    public static Pair<State, State> fromSymbol(RegExp symbol) {
        State start = new State(false);
        State end = new State(true);
        start.addTransition(end, symbol);
        return new Pair<>(start, end);
    }
}
