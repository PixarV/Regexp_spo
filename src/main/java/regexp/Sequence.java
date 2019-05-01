package regexp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Sequence extends RegExp {
    List<RegExp> factors = new LinkedList<>();

    public Sequence(RegExp... regExps) {
        factors.addAll(Arrays.asList(regExps));
    }

    public void addRegEx(RegExp e) {
        factors.add(e);
    }

    public int getSize() {
        return factors.size();
    }

    public RegExp getFirst()  {
        return factors.get(0);
    }
}
