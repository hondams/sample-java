package com.github.hondams.fw.codeset;

import java.util.List;
import java.util.Locale;

public interface EnumCode extends Code, CodeSet {

    @Override
    default String getCodeSetType() {
        return EnumCodeSetUtils.getCodeSetType(getClass());
    }

    @Override
    default List<Code> getCodes() {
        return EnumCodeSetUtils.getCodes(getClass());
    }

    @Override
    default String getLabel() {
        return EnumCodeSetUtils.getLabel(this);
    }

    @Override
    default String getLabel(Locale locale) {
        return EnumCodeSetUtils.getLabel(this, locale);
    }
}
