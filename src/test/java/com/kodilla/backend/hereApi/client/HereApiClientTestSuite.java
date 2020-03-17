package com.kodilla.backend.hereApi.client;

import com.kodilla.backend.domain.LocationDto;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.hereApi.domain.HereApiLocation;
import org.junit.Assert;
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

    @Test
    public void getCityGeocodeTest() {
        //When
        List<Double> position = hereApiClient.getCityGeocode("Kraków, Poland");
        //Then
        Assert.assertEquals(2, position.size());
        Assert.assertNotEquals(0.0, position.get(0));
        Assert.assertNotEquals(0.0, position.get(1));
    }

    @Test
    public void searchLocationsTest() {
        //When
        List<HereApiLocation> response = hereApiClient.searchLocations(42.36399, -71.05493, "restaurant", "USA");
        //Then
        Assert.assertTrue(response.size() > 0);
    }

    @Test
    public void searchRouteLength() {
        //Given
        LocationDto origin = new LocationDto(1L, "origin", 49.550667, 19.746695, null, null);
        LocationDto destination = new LocationDto(2L, "dest", 55.617927, 12.650670, null, null);
        Map<String, Boolean> options = new HashMap<>();
        options.put("Express", true);
        OrderRequestDto orderRequest = new OrderRequestDto(null, origin, destination, options, "EUR");
        //When
        Integer length = hereApiClient.searchRouteLength(orderRequest);
        System.out.println("Route length: " + length);
    }
}
