package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.LocationDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationMapperTestSuite {
    
    @Autowired
    private LocationMapper mapper;

    @Test
    public void MapToLocationTest() {
        //Given
        LocationDto locationDto = new LocationDto(123L, "location", 123.2, 321.2, new ArrayList<>(), new ArrayList<>());
        //When
        Location location = mapper.mapToLocation(locationDto);
        //Then
        Assert.assertEquals(location.getId(), locationDto.getId());
        Assert.assertEquals(location.getLabel(), locationDto.getLabel());
        Assert.assertEquals(location.getLatitude(), locationDto.getLatitude(), 0.0001);
        Assert.assertEquals(location.getLongitude(), locationDto.getLongitude(), 0.0001);
        Assert.assertEquals(location.getOrdersFrom(), locationDto.getOrdersFrom());
        Assert.assertEquals(location.getOrdersTo(), locationDto.getOrdersTo());
    }


    @Test
    public void MapToLocationDtoTest() {
        //Given
        Location location = new Location(123L, "location", 123.2, 321.2, new ArrayList<>(), new ArrayList<>());
        //When
        LocationDto locationDto = mapper.mapToLocationDto(location);
        //Then
        Assert.assertEquals(location.getId(), locationDto.getId());
        Assert.assertEquals(location.getLabel(), locationDto.getLabel());
        Assert.assertEquals(location.getLatitude(), locationDto.getLatitude(), 0.0001);
        Assert.assertEquals(location.getLongitude(), locationDto.getLongitude(), 0.0001);
        Assert.assertEquals(location.getOrdersFrom(), locationDto.getOrdersFrom());
        Assert.assertEquals(location.getOrdersTo(), locationDto.getOrdersTo());
    }

    @Test
    public void MapToLocationDtoListTest() {
        //Given
        Location location = new Location(123L, "location", 123.2, 321.2, new ArrayList<>(), new ArrayList<>());
        Location location2 = new Location(1232L, "locatio2n", 1223.2, 3211.2, new ArrayList<>(), new ArrayList<>());
        List<Location> list = new ArrayList<>();
        list.add(location);
        list.add(location2);
        //When
        List<LocationDto> resultList = mapper.mapToLocationDtoList(list);
        //Then
        Assert.assertEquals(2, resultList.size());
    }
}
