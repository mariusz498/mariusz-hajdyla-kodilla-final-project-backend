package com.kodilla.backend.client;

import com.kodilla.backend.config.FrontendConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

public class CompanyRegistrationClient {

    @Autowired
    private FrontendConfig frontendConfig;

    @Autowired
    private RestTemplate restTemplate;


}
