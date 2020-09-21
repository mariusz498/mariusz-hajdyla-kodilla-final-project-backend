package com.kodilla.backend.currencyApi.client;

import com.kodilla.backend.currencyApi.domain.CurrencyRates;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

@Component
@NoArgsConstructor
public class CurrencyApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public Double convert(String symbol) {
        String endpoint = "https://api.exchangeratesapi.io/latest?symbols=";
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + symbol).build().encode().toUri();
        CurrencyRates response = restTemplate.getForObject(url, CurrencyRates.class);
        if (response != null) {
            return ofNullable(response.getRates().get(symbol)).orElse(-1.0);
        }
        else {
            System.out.println("Null response exception!");
            return -1.0;
        }
    }
}
