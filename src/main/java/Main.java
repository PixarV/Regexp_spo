import nfa.MatchUtil;
import nfa.Pair;
import parser.RegexpParser;
import regexp.RegExp;

public class Main {
    public static void main(String[] args) {
        RegexpParser regexpParser = new RegexpParser("(0|(1(01*0)*1))*", 0);
        RegExp regex = regexpParser.regex();
        RegexpParser regexpParser1 = new RegexpParser("(a|b)*d", 0);
        RegExp regex1 = regexpParser1.regex();
    }
}
