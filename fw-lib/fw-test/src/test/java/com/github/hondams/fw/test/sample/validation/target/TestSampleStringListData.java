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

    @Size(min = 1, max = 13)
    @Builder.Default
    private List<String> sizeValues = new ArrayList<>();

    @Size(min = 1)
    @Builder.Default
    private List<String> minSizeValues = new ArrayList<>();

    @Size(max = 3)
    @Builder.Default
    private List<String> maxSizeValues = new ArrayList<>();

    @Size(min = 3, max = 3)
    @Builder.Default
    private List<String> fixSizeValues = new ArrayList<>();

    @Builder.Default
    private List<@NotNull String> notNullItemValues = new ArrayList<>();

    @Builder.Default
    private List<@Pattern(regexp = "^[a-zA-Z0-9]+$") String> patternItemValues = new ArrayList<>();
}
