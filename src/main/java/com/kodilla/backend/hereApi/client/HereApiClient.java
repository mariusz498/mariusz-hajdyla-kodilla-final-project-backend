package com.kodilla.backend.hereApi.client;

import com.kodilla.backend.controller.Template;
import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.hereApi.domain.*;
import com.kodilla.backend.hereApi.config.HereConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class HereApiClient {

    @Autowired
    private HereConfig hereConfig;

    @Autowired
    private Template restTemplate;

    public List<HereApiLocation> searchLocations(Double lat, Double lng, String query, String countryCode) {

        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getSearchEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("at", lat.toString() + "," + lng.toString())
                .queryParam("q", query)
                .queryParam("in", "countryCode:" + countryCode)
                .build().encode().toUri();

        System.out.println(url);

        try {
            HereItem locationResponse = restTemplate.getForObject(url, HereItem.class);
            List<HereApiLocation> locationList;
            locationList = ofNullable(locationResponse.getLocations()).orElse(new HereItem().getLocations());
            return locationList;
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
//TODO change OrderDto to OrderRequest with Location origin, Location destination, options.
    public Integer searchRouteLength(OrderDto order) {

        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getRoutingEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                //TODO add required queryParams
                .build().encode().toUri();

        System.out.println(url);

        try {
            HereApiRoutes routeResponse = restTemplate.getForObject(url, HereApiRoutes.class);
            List<HereRouteSummary> routesList;
            routesList = ofNullable(routeResponse.getSections().get(0).getSummary()).orElse(new ArrayList<>());
            return ofNullable(routesList.get(0).getLenghts().get(0).getLength()).orElse(new Integer(0));
        }
        catch (RestClientException e) {
            return new Integer(0);
        }
    }
}
