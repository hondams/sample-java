package com.github.hondams.fw.charpoint;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
@Getter
@Setter
public class CharPointUtils {

    private CharPointUtilsStrategy strategy = new CharPointUtilsStrategy();

    public boolean isControl(char ch) {
        return strategy.isControl(ch);
    }

    public boolean isHalfWidthAlphabetNumber(char ch) {
        return strategy.isHalfWidthAlphabetNumber(ch);
    }

    public boolean isHalfWidthAlphabet(char ch) {
        return strategy.isHalfWidthAlphabet(ch);
    }

    public boolean isHalfWidthUpperAlphabet(char ch) {
        return strategy.isHalfWidthUpperAlphabet(ch);
    }

    public boolean isHalfWidthLowerAlphabet(char ch) {
        return strategy.isHalfWidthLowerAlphabet(ch);
    }

    public boolean isHalfWidthNumber(char ch) {
        return strategy.isHalfWidthNumber(ch);
    }

    public boolean isHalfWidthAsciiSymbol(char ch) {
        return strategy.isHalfWidthAsciiSymbol(ch);
    }

    public boolean isHalfWidthKatakana(char ch) {
        return strategy.isHalfWidthKatakana(ch);
    }

    public boolean isHalfWidthAscii(char ch) {
        return strategy.isHalfWidthAscii(ch);
    }

    public boolean isFullWidthAlphabetNumber(char ch) {
        return strategy.isFullWidthAlphabetNumber(ch);
    }

    public boolean isFullWidthAlphabet(char ch) {
        return strategy.isFullWidthAlphabet(ch);
    }

    public boolean isFullWidthUpperAlphabet(char ch) {
        return strategy.isFullWidthUpperAlphabet(ch);
    }

    public boolean isFullWidthLowerAlphabet(char ch) {
        return strategy.isFullWidthLowerAlphabet(ch);
    }

    public boolean isFullWidthNumber(char ch) {
        return strategy.isFullWidthNumber(ch);
    }

    public boolean isFullWidthHiragana(char ch) {
        return strategy.isFullWidthHiragana(ch);
    }

    public boolean isFullWidthKatakana(char ch) {
        return strategy.isFullWidthKatakana(ch);
    }

    public boolean isFullWidthAsciiSymbol(char ch) {
        return strategy.isFullWidthAsciiSymbol(ch);
    }

    public boolean isFullWidthAscii(char ch) {
        return strategy.isFullWidthAscii(ch);
    }

    public boolean isHalfWidthWhitespace(char ch) {
        return strategy.isHalfWidthWhitespace(ch);
    }

    public boolean isFullWidthWhitespace(char ch) {
        return strategy.isFullWidthWhitespace(ch);
    }

    public boolean isWhitespace(char ch) {
        return strategy.isWhitespace(ch);
    }
}
