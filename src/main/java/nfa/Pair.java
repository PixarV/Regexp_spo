package nfa;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level=PRIVATE, makeFinal = true)
public class Pair<T, U> {
    T left;
    U right;
}
