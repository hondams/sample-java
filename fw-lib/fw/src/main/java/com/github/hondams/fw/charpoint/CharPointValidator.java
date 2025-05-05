package com.github.hondams.fw.charpoint;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CharPointValidator {

    public boolean isValid(String value, CharPointType... charPointTypes) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (!isValidChar(ch, charPointTypes)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidChar(char value, CharPointType[] charPointTypes) {
        for (CharPointType charPointType : charPointTypes) {
            if (charPointType.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
