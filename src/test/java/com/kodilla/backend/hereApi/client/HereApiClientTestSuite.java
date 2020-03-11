package com.kodilla.backend.hereApi.client;

import com.kodilla.backend.domain.LocationDto;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.hereApi.domain.HereApiLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HereApiClientTestSuite {

    @Autowired
    private HereApiClient hereApiClient;


    //TODO uzupełnić testy o asercje
    @Test
    public void getLocationsTest() {
        //Given
        List<HereApiLocation> response = hereApiClient.searchLocations(42.36399, -71.05493, "restaurant", "USA");
        //When
        response.stream().forEach(r -> System.out.println(r.getAddress().getLabel() + ", " + r.getPosition().getLatitude() + r.getPosition().getLongitude()));
        //Then
    }

    @Test
    public void searchRouteLength() {
        //Given
        LocationDto origin = new LocationDto(1L, "origin", 49.550667, 19.746695, null, null);
        LocationDto destination = new LocationDto(2L, "dest", 55.617927, 12.650670, null, null);
        Map<String, String> options = new HashMap<>();
        options.put("routingMode", "short");
        OrderRequestDto orderRequest = new OrderRequestDto(null, origin, destination, options, "EUR");
        //When
        Integer length = hereApiClient.searchRouteLength(orderRequest);
        System.out.println("Route length: " + length);
    }
}
