package com.github.hondams.fw.test.sample.validation.target;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleStringData {

    @NotNull private String notNullValue;

    @NotEmpty private String notEmptyValue;

    @Size(min = 1, max = 10) private String sizeValue;

    @Pattern(regexp = "^[a-zA-Z0-9]+$") private String patternValue;
}
