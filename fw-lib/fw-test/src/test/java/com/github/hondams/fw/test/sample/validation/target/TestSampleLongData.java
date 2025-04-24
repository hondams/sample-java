package com.github.hondams.fw.test.sample.validation.target;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleLongData {

    @NotNull
    private Long notNullValue;

    @DecimalMax(value = "10")
    private Long maxValue;

    @DecimalMin(value = "10")
    private Long minValue;
}
