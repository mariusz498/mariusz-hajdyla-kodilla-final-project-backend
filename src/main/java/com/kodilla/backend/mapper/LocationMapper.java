package com.kodilla.backend.mapper;


import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.LocationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationMapper {
    public Location mapToLocation(final LocationDto locationDto) {
        return new Location(
                locationDto.getId(),
                locationDto.getLabel(),
                locationDto.getLatitude(),
                locationDto.getLongitude(),
                locationDto.getOrdersFrom(),
                locationDto.getOrdersTo());
    }

    public LocationDto mapToLocationDto(final Location location) {
        return new LocationDto(
                location.getId(),
                location.getLabel(),
                location.getLatitude(),
                location.getLongitude(),
                location.getOrdersFrom(),
                location.getOrdersTo());
    }

    public List<LocationDto> mapToLocationDtoList(final List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<>();
        for(Location location : locations) {
            locationDtos.add(mapToLocationDto(location));
        }
        return locationDtos;
    }
}
