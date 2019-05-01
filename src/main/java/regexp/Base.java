package regexp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Base extends RegExp {
    char c;
}
