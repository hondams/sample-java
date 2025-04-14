package com.github.hondams.fw.test.sample.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.hondams.fw.test.logging.LoggingEventExtension;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(LoggingEventExtension.class)
@Slf4j
class LoggingEventExtensionTest {

    @Test
    void test() {

        List<ILoggingEvent> loggingEvents =
                LoggingEventExtension.getLoggingEvents();
        assertEquals(0, loggingEvents.size());
    }

    @Test
    void test2() {
        log.info("test");
        List<ILoggingEvent> loggingEvents =
                LoggingEventExtension.getLoggingEvents();

                assertEquals(1, loggingEvents.size());
        for (ILoggingEvent loggingEvent : loggingEvents) {
            assertEquals(Level.INFO, loggingEvent.getLevel());
            assertEquals("test", loggingEvent.getMessage());
            assertEquals(LoggingEventExtensionTest.class.getName(), loggingEvent.getLoggerName());
        }
    }
}
