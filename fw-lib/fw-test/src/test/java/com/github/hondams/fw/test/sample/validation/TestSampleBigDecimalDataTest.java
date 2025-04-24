package com.github.hondams.fw.test.sample.validation;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.hondams.fw.test.sample.validation.target.TestSampleBigDecimalData;
import com.github.hondams.fw.test.validation.ValidationTestUtils;

class TestSampleBigDecimalDataTest {

    @ParameterizedTest
    @MethodSource("provideTestDataForTestValidation")
    void testValidation(List<String> expectedMessages,
            TestSampleBigDecimalData.TestSampleBigDecimalDataBuilder builder) {
        assertLinesMatch(expectedMessages, ValidationTestUtils.getValidatedMessages(builder.build(), Locale.JAPAN));
    }

    private static Stream<Object[]> provideTestDataForTestValidation() {
        // フィールドごと、チェック条件ごと、境界値ごとに、エラー・非エラーを確認する。
        return Stream.of(
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                },
                new Object[] {
                        List.of("「BigDecimalの必須の値」は、必ず指定してください"),
                        noErrorBuilder()
                                .notNullValue(null)
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .exclusiveMaxValue(new BigDecimal("9.9999999999"))
                },
                new Object[] {
                        List.of(
                                "「BigDecimalの境界値を含まない最大値」は、10.0 より小さくしてください"),
                        noErrorBuilder()
                                .exclusiveMaxValue(new BigDecimal("10.0"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .maxValue(new BigDecimal("10.0"))
                },
                new Object[] {
                        List.of(
                                "「BigDecimalの境界値を含む最大値」は、10.0 以下にしてください"),
                        noErrorBuilder()
                                .maxValue(new BigDecimal("10.0000000001"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .exclusiveMinValue(new BigDecimal("10.0000000001"))
                },
                new Object[] {
                        List.of(
                                "「BigDecimalの境界値を含まない最小値」は、10.0 より大きくしてください"),
                        noErrorBuilder()
                                .exclusiveMinValue(new BigDecimal("10.0"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .minValue(new BigDecimal("10.0"))
                },
                new Object[] {
                        List.of(
                                "「BigDecimalの境界値を含む最小値」は、10.0 以上にしてください"),
                        noErrorBuilder()
                                .minValue(new BigDecimal("9.9999999999"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .digitsValue(new BigDecimal("0.00"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .digitsValue(new BigDecimal("00000.00"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .digitsValue(new BigDecimal("000000.00"))
                },
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                                .digitsValue(new BigDecimal("99999.99"))
                },
                new Object[] {
                        List.of("「BigDecimalの整数部・小数部の長さを指定した値」は、整数部 5 桁以下、小数部 2 桁以下にしてください"),
                        noErrorBuilder()
                                .digitsValue(new BigDecimal("0.000"))
                },
                new Object[] {
                        List.of("「BigDecimalの整数部・小数部の長さを指定した値」は、整数部 5 桁以下、小数部 2 桁以下にしてください"),
                        noErrorBuilder()
                                .digitsValue(new BigDecimal("100000.00"))
                });
    }

    private static TestSampleBigDecimalData.TestSampleBigDecimalDataBuilder noErrorBuilder() {
        // エラーにならない値を設定する。
        return TestSampleBigDecimalData.builder()//
                .notNullValue(new BigDecimal("0.0"));
    }
}
