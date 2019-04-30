package parser;

import regexp.RegEx;

public interface Notation {
    RegEx regex();
    RegEx term();
    RegEx factor();
    RegEx base();
}
