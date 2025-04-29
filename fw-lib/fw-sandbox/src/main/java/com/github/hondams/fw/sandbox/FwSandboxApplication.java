package com.github.hondams.fw.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class FwSandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FwSandboxApplication.class, args);
        log.info("FwSandboxApplication started {}", System.getProperty("user.dir"));
    }

}
