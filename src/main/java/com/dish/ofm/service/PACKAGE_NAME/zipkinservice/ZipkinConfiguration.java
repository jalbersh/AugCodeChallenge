package com.dish.ofm.service.PACKAGE_NAME.zipkinservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.sleuth.stream.HostLocator;
import org.springframework.cloud.sleuth.stream.ServerPropertiesHostLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipkinConfiguration {

    @Autowired
    private ServerProperties serverProperties;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }

    @Bean
    public HostLocator hostLocator() {
        return new ServerPropertiesHostLocator(serverProperties, appName);
    }
}
