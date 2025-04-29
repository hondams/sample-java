package com.github.hondams.fw.test.sample.validation;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.hondams.fw.test.sample.validation.target.TestSampleDefaultValidationData;
import com.github.hondams.fw.test.validation.ValidationTestUtils;

class TestSampleDefaultValidationDataTest {

    @ParameterizedTest
    @MethodSource("provideTestDataForTestValidation")
    void testValidation(List<String> expectedMessages,
            TestSampleDefaultValidationData.TestSampleDefaultValidationDataBuilder builder) {
        assertLinesMatch(expectedMessages,
                ValidationTestUtils.getValidatedMessages(builder.build(), Locale.JAPAN));
    }

    private static Stream<Object[]> provideTestDataForTestValidation() {

        // フィールドごと、チェック条件ごと、境界値ごとに、エラー・非エラーを確認する。
        return Stream.of(//
                new Object[] {List.of(//
                        "【利用方法誤り】messageプロパティを指定して利用して下さい。"), //
                        noErrorBuilder()//
                                .assertFalseValue(true)},
                new Object[] {List.of(//
                        "【利用方法誤り】messageプロパティを指定して利用して下さい。"), //
                        noErrorBuilder()//
                                .assertTrueValue(false)},
                new Object[] {List.of(//
                        "【利用方法誤り】DecimalRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .decimalMaxValue(new BigDecimal("3.1"))},
                new Object[] {List.of(//
                        "【利用方法誤り】DecimalRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .decimalMinValue(new BigDecimal("2.9"))},
                new Object[] {List.of(//
                        "【利用方法誤り】FractionDigitsを利用して下さい。"), //
                        noErrorBuilder()//
                                .digitsValue(new BigDecimal("0.000"))},
                new Object[] {List.of(//
                        "【利用方法誤り】ValueTypeを利用して下さい。"), //
                        noErrorBuilder()//
                                .emailValue("abc")},
                new Object[] {List.of(//
                        "「未来日の値」は、未来日を指定してください"), //
                        noErrorBuilder()//
                                .futureValue(LocalDateTime.now().minusDays(1))},
                new Object[] {List.of(//
                        "【利用方法誤り】Futureを利用して下さい。"), //
                        noErrorBuilder()//
                                .futureOrPresentValue(LocalDateTime.now().minusDays(1))},
                new Object[] {List.of(//
                        "【利用方法誤り】LongRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .maxValue(4L)},
                new Object[] {List.of(//
                        "【利用方法誤り】LongRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .minValue(2L)},
                new Object[] {List.of(//
                        "【利用方法誤り】LongRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .negativeValue(0L)},
                new Object[] {List.of(//
                        "【利用方法誤り】LongRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .negativeOrZeroValue(1L)},
                new Object[] {List.of(//
                        "【利用方法誤り】StringLengthを利用して下さい。"), //
                        noErrorBuilder()//
                                .notBlankValue(" ")},
                new Object[] {List.of(//
                        "【利用方法誤り】StringLengthを利用して下さい。"), //
                        noErrorBuilder()//
                                .notEmptyValue("")},
                new Object[] {List.of(//
                        "「Stringの必須の値」は、必ず指定してください"), //
                        noErrorBuilder()//
                                .notNullValue(null)},
                new Object[] {List.of(//
                        "「Stringの未指定の値」は、指定しないでください"), //
                        noErrorBuilder()//
                                .nullValue("")},
                new Object[] {List.of(//
                        "【利用方法誤り】Pastを利用して下さい。"), //
                        noErrorBuilder()//
                                .pastOrPresentValue(LocalDateTime.now().plusDays(1))},
                new Object[] {List.of(//
                        "「過去日の値」は、過去日を指定してください"), //
                        noErrorBuilder()//
                                .pastValue(LocalDateTime.now().plusDays(1))},
                new Object[] {List.of(//
                        "【利用方法誤り】できるだけ、ValueTypeを利用して下さい。利用する場合は、messageプロパティを指定して利用して下さい。"), //
                        noErrorBuilder()//
                                .patternValue("1")},
                new Object[] {List.of(//
                        "【利用方法誤り】LongRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .positiveOrZeroValue(-1L)},
                new Object[] {List.of(//
                        "【利用方法誤り】LongRangeを利用して下さい。"), //
                        noErrorBuilder()//
                                .positiveValue(0L)},
                new Object[] {List.of(//
                        "【利用方法誤り】maxプロパティを指定して利用して下さい。"), //
                        noErrorBuilder()//
                                .minOnlySizeValue(List.of())},
                new Object[] {List.of(//
                        "「最大要素数のみ文字列リストの値」は、3 個 以下にしてください"), //
                        noErrorBuilder()//
                                .maxOnlySizeValue(List.of("", "", "", ""))},
                new Object[] {List.of(//
                        "「範囲要素数の文字列リストの値」は、2 個 以上、5 個 以下にしてください"), //
                        noErrorBuilder()//
                                .rangeSizeValue(List.of(""))},
                new Object[] {List.of(//
                        "「固定要素数の文字列リストの値」は、3 個 にしてください"), //
                        noErrorBuilder()//
                                .fixSizeValue(List.of("", ""))},
                new Object[] {List.of(), //
                        noErrorBuilder()});
    }

    private static TestSampleDefaultValidationData.TestSampleDefaultValidationDataBuilder noErrorBuilder() {
        // エラーにならない値を設定する。
        return TestSampleDefaultValidationData.builder()//
                .notNullValue("")//
                .notEmptyValue("abc")//
                .notBlankValue("abc");
    }
}
