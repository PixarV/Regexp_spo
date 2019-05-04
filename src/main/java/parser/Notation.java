package parser;

import regexp.RegExp;

public interface Notation {
    RegExp regexp();
    RegExp term();
    RegExp factor();
    RegExp base();
}
