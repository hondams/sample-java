package com.github.hondams.fw.logging;

import org.springframework.core.Ordered;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class OrderedLoggingComponent implements Ordered {

    private final int order;

    public OrderedLoggingComponent(int order) {
        this.order = order;
        log.debug("construct of [order={}]", getOrderString());
    }

    @PostConstruct
    public void init() {
        log.debug("post construct of [order={}]", getOrderString());
    }

    @PreDestroy
    public void destroy() {
        log.debug("pre destroy of [order={}]", getOrderString());
    }

    private String getOrderString() {
        switch (this.order) {
            case Ordered.LOWEST_PRECEDENCE:
                return "LOWEST_PRECEDENCE";
            case Ordered.HIGHEST_PRECEDENCE:
                return "HIGHEST_PRECEDENCE";
            default:
                return String.valueOf(this.order);
        }
    }
}
