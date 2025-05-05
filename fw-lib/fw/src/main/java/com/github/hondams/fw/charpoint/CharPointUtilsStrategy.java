package com.github.hondams.fw.charpoint;


public class CharPointUtilsStrategy {

    public boolean isControl(char ch) {
        return ch < 0x20 // 0x20:space
                || ch == 0x7F; // 0x7F:delete
    }

    public boolean isHalfWidthAlphabetNumber(char ch) {
        return isHalfWidthUpperAlphabet(ch) || isHalfWidthLowerAlphabet(ch)
                || isHalfWidthNumber(ch);
    }

    public boolean isHalfWidthAlphabet(char ch) {
        return isHalfWidthUpperAlphabet(ch) || isHalfWidthLowerAlphabet(ch);
    }

    public boolean isHalfWidthUpperAlphabet(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }

    public boolean isHalfWidthLowerAlphabet(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    public boolean isHalfWidthNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public boolean isHalfWidthAsciiSymbol(char ch) {
        return (0x21 <= ch // 0x21:exclamation mark
                && ch <= 0x2F) // 0x2F:slash
                || (0x3A <= ch // 0x3A:colon
                        && ch <= 0x40) // 0x40:commercial at
                || (0x5B <= ch // 0x5B:left square bracket
                        && ch <= 0x60) // 0x60:grave accent
                || (0x7B <= ch // 0x7B:left curly bracket
                        && ch <= 0x7E); // 0x7E:tilde
    }

    public boolean isHalfWidthKatakana(char ch) {
        return 0xFF66 <= ch // 0xFF66:HALFWIDTH KATAKANA LETTER WO
                && ch <= 0xFF9F;// 0xFF9F:HALFWIDTH KATAKANA SEMI-VOICED SOUND MARK
    }

    public boolean isHalfWidthAscii(char ch) {
        return 0x21 <= ch // 0x21:exclamation mark
                && ch <= 0x7E; // 0x7E:tilde
    }

    public boolean isFullWidthAlphabetNumber(char ch) {
        return isFullWidthUpperAlphabet(ch) || isFullWidthLowerAlphabet(ch)
                || isFullWidthNumber(ch);
    }

    public boolean isFullWidthAlphabet(char ch) {
        return isFullWidthUpperAlphabet(ch) || isFullWidthLowerAlphabet(ch);
    }

    public boolean isFullWidthUpperAlphabet(char ch) {
        return 'Ａ' <= ch && ch <= 'Ｚ';
    }

    public boolean isFullWidthLowerAlphabet(char ch) {
        return 'ａ' <= ch && ch <= 'ｚ';
    }

    public boolean isFullWidthNumber(char ch) {
        return '０' <= ch && ch <= '９';
    }

    public boolean isFullWidthHiragana(char ch) {
        return 0x3040 <= ch && ch <= 0x309F;// Hiragana:U+3040..U+309F
    }

    public boolean isFullWidthKatakana(char ch) {
        return (0x30A0 <= ch && ch <= 0x30FF)// Katakana:U+30A0..U+30FF
                || (0x31F0 <= ch && ch <= 0x31FF); // Katakana Phonetic Extensions:U+31F0..U+31FF;
    }

    public boolean isFullWidthAsciiSymbol(char ch) {
        return (0xFF01 <= ch // 0xFF01:fullwidth exclamation mark
                && ch <= 0xFF0F) // 0xFF0F:fullwidth solidus
                || (0xFF1A <= ch // 0xFF1A:fullwidth colon
                        && ch <= 0xFF20) // 0xFF20:fullwidth commercial at
                || (0xFF3B <= ch // 0xFF3B:fullwidth left square bracket
                        && ch <= 0xFF40) // 0xFF40:fullwidth grave accent
                || (0xFF5B <= ch // 0xFF5B:fullwidth left curly bracket
                        && ch <= 0xFF5E); // 0xFF5E:fullwidth tilde
    }

    public boolean isFullWidthAscii(char ch) {
        return 0xFF01 <= ch // 0xFF01:fullwidth exclamation mark
                && ch <= 0xFF5E; // 0xFF5E:fullwidth tilde
    }

    public boolean isHalfWidthWhitespace(char ch) {
        return ch == 0x20 // 0x20:space
                || ch == '\t' // 0x09:horizontal tab
                || ch == '\n' // 0x0A:line feed
                || ch == '\r' // 0x0D:carriage return
                || ch == '\f'; // 0x0C:form feed
    }

    public boolean isFullWidthWhitespace(char ch) {
        return ch == 0x3000; // 0x3000:IDEOGRAPHIC SPACE
    }

    public boolean isWhitespace(char ch) {
        return isHalfWidthWhitespace(ch) || isFullWidthWhitespace(ch);
    }
}
