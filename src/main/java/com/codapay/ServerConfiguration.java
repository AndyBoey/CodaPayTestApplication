package com.codapay;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
@Getter
@Setter
public class ServerConfiguration {
    private String host = "localhost";
    private String port;
    private String instanceId;
}
