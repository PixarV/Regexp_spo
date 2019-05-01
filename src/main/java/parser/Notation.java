package parser;

import regexp.RegExp;

public interface Notation {
    RegExp regex();
    RegExp term();
    RegExp factor();
    RegExp base();
}
