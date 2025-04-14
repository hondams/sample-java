package com.github.hondams.fw.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TestSampleStringData {

    @NotEmpty
    private String notEmptyValue;

    @Size(min = 1, max = 10)
    private String sizeValue;

    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String patternValue;
}
