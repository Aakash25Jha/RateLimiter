package com.aakash.RateLimiter.DemoApplication.Operations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SubtractionOperationConfiguration {

    @Bean
    SubtractionOperation subtractionOperation() {
        return new SubtractionOperation();
    }
}
