package com.github.hondams.fw.validation;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleBigDecimalData {

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
