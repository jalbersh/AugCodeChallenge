package com.dish.ofm.service;

import com.dish.ofm.commonutil.annotations.EnableBeaconRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableBeaconRestTemplate
@SpringBootApplication
public class APPLICATION_NAMEApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAMEApplication.class);

    public static void main(String args[]) {
        SpringApplication.run(APPLICATION_NAMEApplication.class, args);
        LOGGER.info("Finished APPLICATION_NAMEApplication initialization...");
    }
}
