package parser;

import org.junit.Before;
import org.junit.Test;
import regexp.*;

import static enums.Quantifier.NONE;
import static enums.Quantifier.STAR;
import static org.junit.Assert.*;

public class RegexpParserTest {

    private RegExp expectedEasy;
    private RegExp expectedHard;

    @Before
    public void setUp() throws Exception {
        expectedEasy = new Sequence(
                Factor.builder()
                        .base(Choice.builder()
                                .one(Factor.builder()
                                        .base(new Base('a'))
                                        .quantifier(NONE)
                                        .build())
                                .two(Factor.builder()
                                        .base(new Base('b'))
                                        .quantifier(NONE)
                                        .build())
                                .build())
                        .quantifier(STAR)
                        .build(),
                Factor.builder()
                        .base(new Base('d'))
                        .quantifier(NONE)
                        .build());

        expectedHard = Factor.builder()
                .base(Choice.builder()
                        .one(Factor.builder()
                                .base(new Base('0'))
                                .quantifier(NONE)
                                .build())
                        .two(Factor.builder()
                                .base(new Sequence((
                                        Factor.builder()
                                                .base(new Base('1'))
                                                .quantifier(NONE)
                                                .build()),
                                        Factor.builder()
                                                .base(new Sequence(
                                                        Factor.builder()
                                                                .base(new Base('0'))
                                                                .quantifier(NONE)
                                                                .build(),
                                                        Factor.builder()
                                                                .base(new Base('1'))
                                                                .quantifier(STAR)
                                                                .build(),
                                                        Factor.builder()
                                                                .base(new Base('0'))
                                                                .quantifier(NONE)
                                                                .build()
                                                ))
                                                .quantifier(STAR)
                                                .build(),
                                        Factor.builder()
                                                .base(new Base('1'))
                                                .quantifier(NONE)
                                                .build()))
                                .quantifier(NONE)
                                .build())
                        .build())
                .quantifier(STAR)
                .build();
    }

    @Test
    public void easyRegex() {
        RegexpParser regexpParser = new RegexpParser("(a|b)*d", 0);
        RegExp actual = regexpParser.regexp();
        assertEquals(actual, expectedEasy);
    }

    @Test
    public void hardRegex() {
        RegexpParser regexpParser = new RegexpParser("(0|(1(01*0)*1))*", 0);
        RegExp actual = regexpParser.regexp();
        assertEquals(actual, expectedHard);
    }
}