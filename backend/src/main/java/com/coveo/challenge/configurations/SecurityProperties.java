package com.coveo.challenge.configurations;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "coveo.service.challenge.security")
public class SecurityProperties {
    private String allowedOrigins;

    public List<String> getAllowedOrigins() {
        return Arrays.stream(allowedOrigins.split(",")).toList();
    }
}
