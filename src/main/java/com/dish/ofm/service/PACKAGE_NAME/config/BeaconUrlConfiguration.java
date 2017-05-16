package com.dish.ofm.service.PACKAGE_NAME.config;

import com.dish.ofm.service.PACKAGE_NAME.model.BeaconUrls;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static com.dish.ofm.service.PACKAGE_NAME.utils.MapBuilder.mapBuilder;
import static com.dish.ofm.service.PACKAGE_NAME.utils.StringHelpers.interpolateString;
import static com.dish.ofm.service.PACKAGE_NAME.utils.StringHelpers.lowerSnakeToLowerCamel;
import static java.util.stream.Collectors.toMap;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class BeaconUrlConfiguration {
    private Map<String, String> urls;

    @Value("${endpoint_bases.pivotal_ofm}")
    private String endpointBasePivotalOfm;

    @Value("${endpoint_bases.default}")
    private String endpointBaseDefault;

    @Bean
    public BeaconUrls beaconUrls() {
        Map<String, String> urls = getUrls().entrySet()
            .stream()
            .collect(toMap(this::camelCasify, this::interpolateEndpoint));
        return new BeaconUrls(urls);
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    private Map<String, String> endpointBases() {
        return mapBuilder()
            .put("default", endpointBaseDefault)
            .put("pivotal_ofm", endpointBasePivotalOfm)
            .build();
    }

    private String interpolateEndpoint(Map.Entry<String, String> entry) {
        return interpolateString(entry.getValue(), endpointBases());
    }

    private String camelCasify(Map.Entry<String, String> entry) {
        return lowerSnakeToLowerCamel(entry.getKey());
    }
}
