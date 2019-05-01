import parser.RegexpParser;
import regexp.RegExp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hey");
        RegexpParser regexpParser = new RegexpParser("(0|(1(01*0)*1))*", 0);
        RegExp regex = regexpParser.regex();
        System.out.println("hoi");
        RegexpParser regexpParser1 = new RegexpParser("(a|b)*d", 0);
        RegExp regex1 = regexpParser1.regex();
        System.out.println("hoi");
    }
}
