package com.github.hondams.fw.test.sample.validation.target;

import java.math.BigDecimal;

import org.terasoluna.gfw.common.validator.constraints.Compare;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleBigDecimalData {

    @NotNull
    private BigDecimal notNullValue;

    @DecimalMax(value = "10.0", inclusive = false)
    private BigDecimal exclusiveMaxValue;

    @DecimalMax(value = "10.0")
    private BigDecimal maxValue;

    @DecimalMin(value = "10.0", inclusive = false)
    private BigDecimal exclusiveMinValue;

    @DecimalMin(value = "10.0")
    private BigDecimal minValue;

    @Digits(integer = 5, fraction = 2)
    private BigDecimal digitsValue;
}
