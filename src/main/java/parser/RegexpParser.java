package parser;

import enums.Quantifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import regexp.*;

import static enums.Quantifier.*;
import static lombok.AccessLevel.PRIVATE;

@Data
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RegexpParser implements Notation {
    final String input;
    int index;

    private char next() {
        return input.charAt(index++);
    }

    private char peek() {
        return input.charAt(index);
    }

    private void eat() {
        index++;
    }

    private boolean isEnd() {
        return index == input.length();
    }

    @Override
    public RegExp regex() {
        if (isEnd()) log.info("empty input");
        log.info("From regex: " + peek());
        RegExp firstPart = term();

        if (!isEnd() && peek() == '|') {
            eat();
            RegExp secondPart = regex();
            return new Choice(firstPart, secondPart);
        } else return firstPart;
    }

    @Override
    public RegExp term() {
        Sequence sequence = new Sequence();
        //if (isEnd()) throw new IllegalStateException("error in term");

        while (!isEnd() && peek() != '|' && peek() != ')') {
            log.info("From term: " + peek());
            RegExp factor = factor();
            sequence.addRegEx(factor);
        }
        return (sequence.getSize() == 1) ? sequence.getFirst() : sequence;
    }

    @Override
    public RegExp factor() {
        RegExp base = base();
        Factor factor = new Factor(base, NONE);

        if (!isEnd()) {
            log.info("From base.quantifier" + peek());
            char c = peek();
            factor.setQuantifier(parseQuantifier(c));
        }
        return factor;
    }

    private Quantifier parseQuantifier(char c) {
        for (Quantifier q : Quantifier.values()) {
            if (q.getC() == c) {
                eat();
                return q;
            }
        }
        return NONE;
    }

    @Override
    public RegExp base() {
        if (isEnd()) throw new IllegalStateException("Empty error in base");
        log.info("From base: " + peek());

        if (peek() == '(') {
            eat();
            RegExp regex = regex();
            if (isEnd() || peek() != ')')
                throw new IllegalStateException(") doesnt found");
            eat();
            return regex;
        }
        return new Base(next());
    }
}
