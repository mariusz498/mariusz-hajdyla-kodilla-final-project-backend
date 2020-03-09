package com.kodilla.backend.hereApi.client;

import com.kodilla.backend.hereApi.domain.HereApiLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HereApiClientTestSuite {

    @Autowired
    private HereApiClient hereApiClient;

    @Test
    public void getLocationsTest() {
        //Given
        List<HereApiLocation> response = hereApiClient.getLocations(42.36399, -71.05493, "restaurant", "USA");
        //When
        response.stream().forEach(r -> System.out.println(r.getAddress().getLabel() + ", " + r.getPosition().getLatitude() + r.getPosition().getLongitude()));
        //Then
    }
}
