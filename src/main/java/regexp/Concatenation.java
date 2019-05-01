package regexp;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import sun.misc.Regexp;

import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Concatenation extends RegEx {
    List<RegEx> regExes = new LinkedList<>();

    public void addRegEx(RegEx e) {
        regExes.add(e);
    }

    public int getSize() {
        return regExes.size();
    }

    public RegEx getFirst()  {
        return regExes.get(0);
    }
}
