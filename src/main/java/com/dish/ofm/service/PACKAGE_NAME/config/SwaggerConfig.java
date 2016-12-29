package com.dish.ofm.service.PACKAGE_NAME.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("service-adapters")
            .apiInfo(apiInfo())
            .select()
            .paths(regex(".*/REST_ENDPOINT.*"))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("PROJECT_TITLE")
            .description("PROJECT_TITLE Dish Offer Management")
            .termsOfServiceUrl("http://terms of service")
            .contact(new Contact("Service Adapters", "", "OFMServiceAdapterTeam@dish.com"))
            .license("Dish Internal Use Only")
            .licenseUrl("http://dishurl copyright")
            .version("0.1")
            .build();
    }
}