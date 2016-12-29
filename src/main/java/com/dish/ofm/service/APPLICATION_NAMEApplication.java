package com.dish.ofm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APPLICATION_NAMEApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAMEApplication.class);

    public static void main(String args[]) {
        SpringApplication.run(APPLICATION_NAMEApplication.class, args);
        LOGGER.info("Finished APPLICATION_NAMEApplication initialization...");
    }
}
