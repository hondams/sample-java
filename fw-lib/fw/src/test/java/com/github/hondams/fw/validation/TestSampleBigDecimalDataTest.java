package com.github.hondams.fw.validation;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.hondams.fw.test.validation.ValidationTestUtils;

class TestSampleBigDecimalDataTest {

    @ParameterizedTest
    @MethodSource("provideTestDataForTestValidation")
    void testValidation(List<String> expectedMessages, TestSampleBigDecimalData.TestSampleBigDecimalDataBuilder builder) {
        assertLinesMatch(expectedMessages, ValidationTestUtils.getValidatedMessages(builder.build()));
    }

    private static Stream<Object[]> provideTestDataForTestValidation() {
        // フィールドごと、チェック条件ごと、境界値ごとに、エラー・非エラーを確認する。
        return Stream.of(
                new Object[] {
                        List.of(),
                        noErrorBuilder()
                },
                new Object[] {
                        List.of(
                                "10.0 より小さな値にしてください"),
                                noErrorBuilder()
                                .maxExclusiveValue(new BigDecimal("10.0"))
                });
    }

    private static TestSampleBigDecimalData.TestSampleBigDecimalDataBuilder noErrorBuilder() {
        // エラーにならない値を設定する。
        return TestSampleBigDecimalData.builder();
    }
}
