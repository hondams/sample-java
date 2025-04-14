package com.github.hondams.fw.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TestSampleIntegerData {

    @Max(10)
    private Integer maxValue;

    @Min(10)
    private Integer minValue;
}
