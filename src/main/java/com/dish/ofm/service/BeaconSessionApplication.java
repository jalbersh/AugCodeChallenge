package com.dish.ofm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BeaconSessionApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(BeaconSessionApplication.class);

    public static void main(String args[]) {
        SpringApplication.run(BeaconSessionApplication.class, args);
        LOGGER.info("Finished BeaconSessionApplication initialization...");
    }
}
