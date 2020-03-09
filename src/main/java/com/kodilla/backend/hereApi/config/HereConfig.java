package com.kodilla.backend.hereApi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class HereConfig {
    @Value("${hereapi.apiKey}")
    private String apiKey;

    @Value("${hereapi.geocode.endpoint}")
    private String geocodeEndpoint;

    @Value("${hereapi.routing.endpoint}")
    private String routingEndpoint;

    @Value("${hereapi.search.endpoint}")
    private String searchEndpoint;
}
