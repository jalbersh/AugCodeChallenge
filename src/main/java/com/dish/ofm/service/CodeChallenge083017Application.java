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
public class CodeChallenge083017Application {
    private static Logger LOGGER = LoggerFactory.getLogger(CodeChallenge083017Application.class);

    public static void main(String args[]) {
        SpringApplication.run(CodeChallenge083017Application.class, args);
        LOGGER.info("Finished CodeChallenge083017Application initialization...");
    }
}
