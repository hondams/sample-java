package com.github.hondams.fw.test.sample.validation.target;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleIntegerData {

    @NotNull private Integer notNullValue;

    @NotNull private Integer nullValue;

    @Max(10) private Integer maxValue;

    @Min(10) private Integer minValue;
}
