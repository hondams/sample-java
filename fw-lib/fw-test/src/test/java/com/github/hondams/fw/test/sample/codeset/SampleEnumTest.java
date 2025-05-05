package com.github.hondams.fw.test.sample.codeset;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SampleEnumTest {

    @Test
    void test() {
        assertEquals("sampleEnum", SampleEnum.A.getCodeSetType());
    }
}
