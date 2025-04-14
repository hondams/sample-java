package com.github.hondams.fw.validation;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class TestSampleLongData {

    @DecimalMax(value = "10")
    private Long maxValue;

    @DecimalMin(value = "10")
    private Long minValue;
}
