package parser;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.Before;
import org.junit.Test;
import regexp.Choice;
import regexp.Factor;
import regexp.RegExp;
import regexp.Sequence;

import static org.junit.Assert.*;

public class RegexpParserTest {

    Sequence one;

    @Before
    public void setUp() throws Exception {
//        one = new Sequence();

//        one.addRegEx(new Choice(new Factor()));
    }

    @Test
    public void regex() {
        RegexpParser regexpParser1 = new RegexpParser("(a|b)*d", 0);
        RegexpParser regexpParser2 = new RegexpParser("(0|(1(01*0)*1))*", 0);


        RegExp regex1 = regexpParser1.regex();
    }

    @Test
    public void term() {
    }

    @Test
    public void factor() {
    }

    @Test
    public void base() {
    }
}