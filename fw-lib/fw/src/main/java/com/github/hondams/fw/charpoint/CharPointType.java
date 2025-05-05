package com.github.hondams.fw.charpoint;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CharPointType {

    CONTROL(CharPointUtils::isControl), //
    HALF_WIDTH_WHITESPACE(CharPointUtils::isHalfWidthWhitespace), //
    HALF_WIDTH_ALPHABET_NUMBER(CharPointUtils::isHalfWidthAlphabet), //
    HALF_WIDTH_ALPHABET(CharPointUtils::isHalfWidthAlphabet), //
    HALF_WIDTH_UPPER_ALPHABET(CharPointUtils::isHalfWidthUpperAlphabet), //
    HALF_WIDTH_LOWER_ALPHABET(CharPointUtils::isHalfWidthLowerAlphabet), //
    HALF_WIDTH_NUMBER(CharPointUtils::isHalfWidthNumber), //
    HALF_WIDTH_ASCII_SYMBOL(CharPointUtils::isHalfWidthAsciiSymbol), //
    HALF_WIDTH_KATAKANA(CharPointUtils::isHalfWidthKatakana), //
    HALF_WIDTH_ASCII(CharPointUtils::isHalfWidthAscii), //
    FULL_WIDTH_ALPHABET_NUMBER(CharPointUtils::isFullWidthAlphabetNumber), //
    FULL_WIDTH_ALPHABET(CharPointUtils::isFullWidthAlphabet), //
    FULL_WIDTH_UPPER_ALPHABET(CharPointUtils::isFullWidthUpperAlphabet), //
    FULL_WIDTH_LOWER_ALPHABET(CharPointUtils::isFullWidthLowerAlphabet), //
    FULL_WIDTH_NUMBER(CharPointUtils::isFullWidthNumber), //
    FULL_WIDTH_HIRAGANA(CharPointUtils::isFullWidthHiragana), //
    FULL_WIDTH_KATAKANA(CharPointUtils::isFullWidthKatakana), //
    FULL_WIDTH_ASCII_SYMBOL(CharPointUtils::isFullWidthAsciiSymbol), //
    FULL_WIDTH_ASCII(CharPointUtils::isFullWidthAscii), //
    FULL_WIDTH_WHITESPACE(CharPointUtils::isFullWidthWhitespace), //
    WHITESPACE(CharPointUtils::isWhitespace);

    private final CharPredicate predicate;

    public boolean contains(char ch) {
        return this.predicate.test(ch);
    }
}
