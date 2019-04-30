package regexp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Factor extends RegEx {
    RegEx base;
    Quantifier quantifier;


    public enum Quantifier {
        STAR,
        PLUS,
        QUESTION,
        NONE
    }
}
