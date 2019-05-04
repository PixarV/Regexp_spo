package regexp;

import enums.Quantifier;
import lombok.*;
import lombok.experimental.FieldDefaults;
import nfa.State;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE)
public class Factor extends RegExp {
    RegExp base;
    Quantifier quantifier;

    @Override
    public State createNFA() { // closure
        return null;
    }
}
