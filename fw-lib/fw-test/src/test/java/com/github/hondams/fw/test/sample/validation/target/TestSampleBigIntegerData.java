package com.github.hondams.fw.test.sample.validation.target;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestSampleBigIntegerData {

    @NotNull
    private BigInteger notNullValue;

    @DecimalMax(value = "10")
    private BigInteger maxValue;

    @DecimalMin(value = "10")
    private BigInteger minValue;
}
