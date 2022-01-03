package me.luraframework.gateway.config;

import me.luraframework.gateway.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

    @Bean
    public TokenFilter tokenFilter(ApplicationProperties authProperties, WebClient.Builder builder) {
        return new TokenFilter(authProperties, builder);
    }
}
