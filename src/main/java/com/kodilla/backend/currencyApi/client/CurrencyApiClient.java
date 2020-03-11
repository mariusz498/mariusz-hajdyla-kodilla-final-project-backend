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

    private String endpoint = "https://api.exchangeratesapi.io/latest?symbols=";

    public Double convert(String symbol) {
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + symbol).build().encode().toUri();
        CurrencyRates response = restTemplate.getForObject(url, CurrencyRates.class);
        Double value = ofNullable(response.getRates().get(symbol)).orElse(-1.0);
        return value;
    }
}
