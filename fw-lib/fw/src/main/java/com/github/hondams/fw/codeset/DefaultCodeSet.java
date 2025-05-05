package com.github.hondams.fw.codeset;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class DefaultCodeSet implements CodeSet {

    private final String codeSetType;
    private final List<Code> codes;

    public DefaultCodeSet(String codeSetType, List<Code> codes) {
        this.codeSetType = codeSetType;
        this.codes = Collections.unmodifiableList(codes);
    }
}
