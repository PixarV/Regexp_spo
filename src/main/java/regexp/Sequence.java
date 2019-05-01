package regexp;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Sequence extends RegExp {
    List<RegExp> regExps = new LinkedList<>();

    public void addRegEx(RegExp e) {
        regExps.add(e);
    }

    public int getSize() {
        return regExps.size();
    }

    public RegExp getFirst()  {
        return regExps.get(0);
    }
}
