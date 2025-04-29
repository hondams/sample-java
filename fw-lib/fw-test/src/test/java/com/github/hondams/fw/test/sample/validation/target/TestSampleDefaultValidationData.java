package com.github.hondams.fw.test.sample.validation.target;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSampleDefaultValidationData {

    @AssertTrue private Boolean assertTrueValue;

    @AssertFalse private Boolean assertFalseValue;

    @DecimalMax(value = "3.0") private BigDecimal decimalMaxValue;

    @DecimalMin(value = "3.0") private BigDecimal decimalMinValue;

    @Digits(integer = 3, fraction = 2) private BigDecimal digitsValue;

    @Email private String emailValue;

    @Future private LocalDateTime futureValue;

    @FutureOrPresent private LocalDateTime futureOrPresentValue;

    @Max(3) private Long maxValue;

    @Min(3) private Long minValue;

    @Negative private Long negativeValue;

    @NegativeOrZero private Long negativeOrZeroValue;

    @NotBlank private String notBlankValue;

    @NotEmpty private String notEmptyValue;

    @NotNull private String notNullValue;

    @Null private String nullValue;

    @Past private LocalDateTime pastValue;

    @PastOrPresent private LocalDateTime pastOrPresentValue;

    @Pattern(regexp = "^[a-z]+$") private String patternValue;

    @Positive private Long positiveValue;

    @PositiveOrZero private Long positiveOrZeroValue;

    @Size(min = 3) private List<String> minOnlySizeValue;

    @Size(max = 3) private List<String> maxOnlySizeValue;

    @Size(min = 2, max = 5) private List<String> rangeSizeValue;

    @Size(min = 3, max = 3) private List<String> fixSizeValue;
}
