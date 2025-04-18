package com.github.hondams.fw.test.sample.validation.target;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TestSampleStringListData {

    @NotEmpty
    private List<@NotEmpty String> notEmptyValues = new ArrayList<>();

    @Size(min = 1, max = 10)
    private List<@Size(min = 1, max = 10) String> sizeValues = new ArrayList<>();

    
    private List<@Pattern(regexp = "^[a-zA-Z0-9]+$") String> patternValue = new ArrayList<>();
}
