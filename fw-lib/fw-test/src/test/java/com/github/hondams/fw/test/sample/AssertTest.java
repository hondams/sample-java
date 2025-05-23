package com.github.hondams.fw.test.sample;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.hondams.fw.test.target.TestSampleException;

class AssertTest {

    @Test
    void testFail() {
        boolean fail = false;
        if (fail) {
            // 実行してはいけないルートは、fail()を実行し、実行しないことを確認する。
            fail();
        }
    }

    @Test
    void testAssertTrue() {
        boolean testingCondition = true;
        assertTrue(testingCondition);
    }

    @Test
    void testAssertFalse() {
        boolean testingCondition = false;
        assertFalse(testingCondition);
    }

    @Test
    void testAssertNull() {
        Object testingValue = null;
        assertNull(testingValue);
    }

    @Test
    void testAssertNotNull() {
        Object testingValue = new Object();
        assertNotNull(testingValue);
    }

    @Test
    void testAssertEquals() {
        String testingValue = "A";
        assertEquals("A", testingValue);
    }

    @Test
    void testAssertNotEquals() {
        String testingValue = "not A";
        assertNotEquals("A", testingValue);
    }

    @Test
    void testAssertThrows() {

        // 本来は、以下を実装するべきであるが、オーソドックスに、try-catchを利用する。
        // assertThrowsExactly(TestSampleException.class, () -> {
        // throw new TestSampleException("error message.");
        // });


        try {
            Runnable run = () -> {
                throw new TestSampleException("error message.");
            };
            run.run();
            // 例外が発生しない場合は、以下でエラーにする。
            fail();
        } catch (TestSampleException e) {
            // 例外クラスは、assertEqualsで、同値確認する。
            assertEquals(TestSampleException.class, e.getClass());
            // エラーメッセージも、確認する。
            assertEquals("error message.", e.getMessage());
        }
    }

    @Test
    void testAssertSame() {
        Object testingValue = new Object();
        Object expectedValue = testingValue;
        // 同じインスタンスをチェックする場合に利用する。
        // オブジェクトとしての同値をチェックする場合は、assertEqualsを利用すること。
        assertSame(expectedValue, testingValue);
    }

    @Test
    void testAssertNotSame() {
        Object testingValue = new Object();
        Object expectedValue = new Object();
        assertNotSame(expectedValue, testingValue);
    }

    @Test
    void testAssertArrayEquals() {
        String[] testingValue = {"value1", "value2", "value3"};
        assertArrayEquals(new String[] {"value1", "value2", "value3"}, testingValue);
    }

    @Test
    void testAssertIterableEquals() {
        Collection<String> testingValue = List.of("value1", "value2", "value3");
        assertIterableEquals(List.of("value1", "value2", "value3"), testingValue);
    }

    @Test
    void testAssertLinesMatch() {
        // 「\\d+ms」や「.{3}」は正規表現。
        // 「>> >>」は、次のデータがマッチするまでスキップする。「>> コメント >>」のように間に文字列を書いた場合はコメント扱いで無視されるが、
        // 「>> 3 >>」のように数値を書いた場合はその行数分スキップする。
        List<String> values = List.of(//
                "line1", "line2", "line3", "line4", "line5", //
                "line6", "line7", "line8", "line9", "line10");
        assertLinesMatch(
                List.of("line1", ">> 2 >>", "line4", "l.\\w.\\d", "line6", ">> skip >>", "line10"),
                values);
    }

    @Test
    void testAssertTimeout() {
        // 処理の完了を待たずに、assertエラーをスローするので、
        // assertTimeoutの利用を推奨する。
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            /* 2秒以内の処理 */});
    }
}
