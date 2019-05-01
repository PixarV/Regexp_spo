package regexp;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Base extends RegExp {
    char c;
}
