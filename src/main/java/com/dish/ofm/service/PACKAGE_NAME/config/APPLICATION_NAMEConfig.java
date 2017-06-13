package com.dish.ofm.service.PACKAGE_NAME.config;

import com.dish.core.metrics.health.RestServiceHealthStatusIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class APPLICATION_NAMEConfig {
    @Bean
    public HealthIndicator getRestHealthIndicator() {
        List<String> uris = new LinkedList<>();
        return new RestServiceHealthStatusIndicator(uris, new RestTemplate());
    }
}
