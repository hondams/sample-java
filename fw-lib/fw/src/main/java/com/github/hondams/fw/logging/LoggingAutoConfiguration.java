package com.github.hondams.fw.logging;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@AutoConfiguration
public class LoggingAutoConfiguration {

    @Bean
    OrderedLoggingComponent lowestOrderedLoggingComponent() {
        return new OrderedLoggingComponent(Ordered.LOWEST_PRECEDENCE);
    }

    @Bean
    OrderedLoggingComponent highestOrderedLoggingComponent() {
        return new OrderedLoggingComponent(Ordered.HIGHEST_PRECEDENCE);
    }
}
