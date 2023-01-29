package com.codapay;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "router")
@Getter
@Setter
public class RouterConfiguration {
    private String host;
    private String port;
}
