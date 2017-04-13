package com.dish.ofm.service.beaconsession.config;

import com.dish.zipkin.OkHttpZipkinInterceptor;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Configuration
public class OkHttpConfiguration {
    @Bean
    public List<Interceptor> okHttpInterceptors(
        OkHttpZipkinInterceptor okHttpZipkinInterceptor,
        MisconfiguredEndpointInterceptor misconfiguredEndpoint,
        ContextTokenInterceptor contextTokenInterceptor,
        LoggingInterceptor loggingInterceptor
    ) {
        return asList(
            okHttpZipkinInterceptor,
            misconfiguredEndpoint,
            contextTokenInterceptor,
            loggingInterceptor
        );
    }

    @Bean
    public OkHttpClient okHttpClient(List<Interceptor> okHttpInterceptors) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(30, TimeUnit.SECONDS);    // socket timeout
        client.interceptors().addAll(okHttpInterceptors);

        return client;
    }
}
