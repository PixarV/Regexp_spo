package parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import regexp.*;

import static lombok.AccessLevel.PRIVATE;
import static regexp.Factor.Quantifier.*;

@Data
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RegexpParser implements Notation {
    String input;
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
    public RegEx regex() {
        if (isEnd()) log.info("empty input");
        log.info("From regex: " + peek());
        RegEx firstPart = term();
//        if (peek() == ')')

        if (isEnd() || peek() == ')') return firstPart;

        if (peek() == '|') {
            eat();
            RegEx secondPart = regex();
            return new Choice(firstPart, secondPart);
        }
        throw new IllegalStateException("error in regex");
    }

    @Override
    public RegEx term() {
        Concatenation sequence = new Concatenation();
        if (isEnd()) throw new IllegalStateException("error in term");

        while (!isEnd() && peek() != '|' && peek() != ')') {
            log.info("From term: " + peek());
            RegEx factor = factor();
            sequence.addRegEx(factor);
        }
        return sequence;
    }

    @Override
    public RegEx factor() {
        RegEx base = base();
        Factor factor = new Factor(base, NONE);

        log.info("From base.qualifier" + peek());
        char c = peek();

        switch (c) {
            case '*':
                eat();
                factor.setQuantifier(STAR);
                break;
            case '+':
                eat();
                factor.setQuantifier(PLUS);
                break;
            case '?':
                eat();
                factor.setQuantifier(QUESTION);
                break;
        }
        return factor;
    }

    @Override
    public RegEx base() {
        if (isEnd()) throw new IllegalStateException("Empty error in base");
        log.info("From base: " + peek());

        if (peek() == '(') {
            eat();
            RegEx regex = regex();
            if (isEnd() || peek() != ')')
                throw new IllegalStateException(") doesnt found");
            eat();
            return regex;
        }
        return new Base(next());
    }
}
