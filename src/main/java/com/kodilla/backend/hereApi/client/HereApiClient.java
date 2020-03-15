package com.kodilla.backend.hereApi.client;

import com.kodilla.backend.controller.Template;
import com.kodilla.backend.domain.OrderRequestDto;
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

    public Double[] getCityGeocode(String query) {
        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getGeocodeEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("q", query)
                .build().encode().toUri();
        try {
            HereItem geocodeResponse = restTemplate.getForObject(url, HereItem.class);
            Double latitude = geocodeResponse.getLocations().get(0).getPosition().getLatitude();
            Double longitude = geocodeResponse.getLocations().get(0).getPosition().getLongitude();
            Double[] position = new Double[2];
            position[0] = latitude;
            position[1] = longitude;
            return position;
        } catch (RestClientException e) {
            return new Double[0];
        }
    }

    public List<HereApiLocation> searchLocations(Double lat, Double lng, String query, String countryCode) {

        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getSearchEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("at", lat.toString() + "," + lng.toString())
                .queryParam("q", query)
                .queryParam("in", "countryCode:" + countryCode)
                .build().encode().toUri();

        try {
            HereItem locationResponse = restTemplate.getForObject(url, HereItem.class);
            List<HereApiLocation> locationList;
            locationList = ofNullable(locationResponse.getLocations()).orElse(new HereItem().getLocations());
            return locationList;
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public Integer searchRouteLength(OrderRequestDto orderRequest) {

        URI url = UriComponentsBuilder.fromHttpUrl(hereConfig.getRoutingEndpoint())
                .queryParam("apiKey", hereConfig.getApiKey())
                .queryParam("origin", orderRequest.getOrigin().getLattitude() + "," + orderRequest.getOrigin().getLongitude())
                .queryParam("destination", orderRequest.getDestination().getLattitude() + "," + orderRequest.getDestination().getLongitude())
                .queryParam("routingMode", orderRequest.getOptions().get(ofNullable("routingMode").orElse("short")))
                .queryParam("transportMode", "truck")
                .queryParam("return", "summary")
                .build().encode().toUri();

        System.out.println(url);

        try {
            HereApiRoutesExample routeResponse = restTemplate.getForObject(url, HereApiRoutesExample.class);
            HereApiRoute route = routeResponse.getRoutes().get(0);
            List<HereApiRouteSections> sections = ofNullable(route.getSections()).orElse(new ArrayList<>());
            Integer length = 0;
            for(HereApiRouteSections section : sections) {
                length += section.getSummary().getLength();
            }
            return length;
        } catch (RestClientException e) {
        }
        return new Integer(-1);
    }
}
