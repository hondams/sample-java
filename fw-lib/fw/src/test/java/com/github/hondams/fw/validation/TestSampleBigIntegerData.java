package com.github.hondams.fw.validation;

import java.math.BigInteger;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class TestSampleBigIntegerData {

    @DecimalMax(value = "10")
    private BigInteger maxValue;

    @DecimalMin(value = "10")
    private BigInteger minValue;
}
