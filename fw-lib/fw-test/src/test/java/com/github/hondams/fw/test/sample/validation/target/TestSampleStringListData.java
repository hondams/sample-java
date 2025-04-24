package com.github.hondams.fw.test.sample.validation.target;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleStringListData {

    @NotNull
    @Builder.Default
    private List<@NotNull String> notNullValues = new ArrayList<>();

    @NotEmpty
    @Builder.Default
    private List<@NotEmpty String> notEmptyValues = new ArrayList<>();

    @Size(min = 1, max = 10)
    @Builder.Default
    private List<@Size(min = 1, max = 10) String> sizeValues = new ArrayList<>();

    @Builder.Default
    private List<@Pattern(regexp = "^[a-zA-Z0-9]+$") String> patternValue = new ArrayList<>();
}
