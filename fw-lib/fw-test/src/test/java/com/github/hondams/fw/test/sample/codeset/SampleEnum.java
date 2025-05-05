package com.github.hondams.fw.test.sample.codeset;

import com.github.hondams.fw.codeset.EnumCode;
import com.github.hondams.fw.codeset.EnumCodeSetType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EnumCodeSetType("sampleEnum")
public enum SampleEnum implements EnumCode {

    A("a"), //
    B("b");

    private final String code;

}
