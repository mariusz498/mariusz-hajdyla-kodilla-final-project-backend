package com.kodilla.backend.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FrontendConfig {
    @Value("localhost:8080")
    private String frontendApiEndpoint;
}
