package regexp;

import enums.Quantifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Factor extends RegExp {
    RegExp base;
    Quantifier quantifier;
}
