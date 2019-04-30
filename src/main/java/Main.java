import lombok.val;
import parser.RegexpParser;
import regexp.RegEx;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hey");
        RegexpParser regexpParser = new RegexpParser("(0|(1(01*0)*1))*", 0);
        RegEx regex = regexpParser.regex();
        System.out.println("hoi");
    }
}
