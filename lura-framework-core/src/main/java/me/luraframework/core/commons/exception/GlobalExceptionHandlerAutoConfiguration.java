package me.luraframework.core.commons.exception;

import org.springframework.context.annotation.Bean;

public class GlobalExceptionHandlerAutoConfiguration {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
