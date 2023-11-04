package com.codebooq.client;

import feign.Request;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static java.util.concurrent.TimeUnit.*;

@Configuration
public class FeignConfig {

    @Value("${codebooq.api.bearer-token}")
    private String apiKey;

    @Value("${codebooq.api.timeout}")
    private long timeout;

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(timeout, SECONDS, timeout, SECONDS, true);
    }


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Api-Key", apiKey);
    }
}
