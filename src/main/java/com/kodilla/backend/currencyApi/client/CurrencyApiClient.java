package com.kodilla.backend.currencyApi.client;

import com.kodilla.backend.controller.Template;
import com.kodilla.backend.currencyApi.domain.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

import static java.util.Optional.ofNullable;

@Component
public class CurrencyApiClient {

    @Autowired
    private Template restTemplate;

    public Double convert(String symbol) {
        String endpoint = "https://api.exchangeratesapi.io/latest?symbols=";
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + symbol).build().encode().toUri();
        CurrencyRates response = restTemplate.getForObject(url, CurrencyRates.class);
        return ofNullable(response.getRates().get(symbol)).orElse(-1.0);
    }
}
