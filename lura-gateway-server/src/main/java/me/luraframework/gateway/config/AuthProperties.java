package me.luraframework.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.auth")
public class AuthProperties {
    private String checkUrl;
    private String loginUrl;
    private String logoutUrl;
}
