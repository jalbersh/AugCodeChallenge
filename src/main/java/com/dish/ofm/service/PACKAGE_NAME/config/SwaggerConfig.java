package com.dish.ofm.service.PACKAGE_NAME.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(regex(".*/REST_ENDPOINT.*")).build()
            .useDefaultResponseMessages(false);
    }

    @Controller
    @Profile(value = "prod")
    public static class SwaggerRedirectController {
        @RequestMapping("/swagger-ui.html")
        public ResponseEntity swagger() {
            throw new ResourceNotFoundException();
        }
    }
}