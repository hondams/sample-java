package com.github.hondams.fw.test.sample.validation.target;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestSampleIntegerData {

    @NotNull
    private Integer notNullValue;

    @Max(10)
    private Integer maxValue;

    @Min(10)
    private Integer minValue;
}
