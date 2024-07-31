package com.payriff.payriff_ms.config;

import feign.Feign;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder().retryer(new Retryer.Default())
                .errorDecoder(new ErrorDecoder.Default());
    }
}