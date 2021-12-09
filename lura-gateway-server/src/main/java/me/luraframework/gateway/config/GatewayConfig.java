package me.luraframework.gateway.config;

import me.luraframework.gateway.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public TokenFilter tokenFilter(AuthProperties authProperties) {
        return new TokenFilter(authProperties);
    }
}
