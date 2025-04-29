package com.github.hondams.fw.test.sample.validation;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.hondams.fw.test.sample.validation.target.TestSampleStringData;
import com.github.hondams.fw.test.validation.ValidationTestUtils;

class TestSampleStringDataTest {

    @ParameterizedTest
    @MethodSource("provideTestDataForTestValidation")
    void testValidation(List<String> expectedMessages,
            TestSampleStringData.TestSampleStringDataBuilder builder) {
        assertLinesMatch(expectedMessages,
                ValidationTestUtils.getValidatedMessages(builder.build(), Locale.JAPAN));
    }

    private static Stream<Object[]> provideTestDataForTestValidation() {
        // フィールドごと、チェック条件ごと、境界値ごとに、エラー・非エラーを確認する。
        return Stream.of(

                new Object[] {List.of(), noErrorBuilder()},
                new Object[] {List.of("「Stringの必須の値」は、必ず指定してください"),
                        noErrorBuilder().notNullValue(null)},
                new Object[] {List.of("「Stringの必須の値」は、必ず指定してください"),
                        noErrorBuilder().notEmptyValue(null)},
                new Object[] {List.of("「Stringの非空白の値」は、1 文字以上にしてください"),
                        noErrorBuilder().notEmptyValue("")},
                new Object[] {List.of("「Stringの文字数指定の値」は、1 文字以上、10 文字以下にしてください"),
                        noErrorBuilder().sizeValue("")},
                new Object[] {List.of("「Stringの文字数指定の値」は、1 文字以上、10 文字以下にしてください"),
                        noErrorBuilder().sizeValue("12345678901")},
                new Object[] {List.of(), noErrorBuilder().sizeValue("1")},
                new Object[] {List.of(), noErrorBuilder().sizeValue("1234567890")},
                new Object[] {List.of("「Stringの文字数指定の値」は、1 文字以上、10 文字以下にしてください"),
                        noErrorBuilder().sizeValue("12345678901")},
                new Object[] {List.of(), noErrorBuilder().patternValue("Aa1")},
                new Object[] {List.of("「Stringのパターン指定の値」は、半角英数字にしてください"),
                        noErrorBuilder().patternValue("-")});
    }

    private static TestSampleStringData.TestSampleStringDataBuilder noErrorBuilder() {
        // エラーにならない値を設定する。
        return TestSampleStringData.builder()//
                .notNullValue("").notEmptyValue("abc");
    }
}
