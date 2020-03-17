package com.kodilla.backend.mapper;


import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.LocationDto;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.service.DbService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DbService dbService;

    public Location mapToLocation(final LocationDto locationDto) {
        List<Order> ordersFrom = new ArrayList<>();
        for(Long id : locationDto.getOrdersFrom()) {
            ordersFrom.add(dbService.getOrder(id).orElse(new Order()));
        }
        List<Order> ordersTo = new ArrayList<>();
        for(Long id : locationDto.getOrdersTo()) {
            ordersTo.add(dbService.getOrder(id).orElse(new Order()));
        }
        return new Location(
                locationDto.getId(),
                locationDto.getLabel(),
                locationDto.getLatitude(),
                locationDto.getLongitude(),
                ordersFrom,
                ordersTo);
    }

    public LocationDto mapToLocationDto(final Location location) {
        List<Long> ordersFrom = new ArrayList<>();
        for(Order order : location.getOrdersFrom()) {
            ordersFrom.add(order.getId());
        }
        List<Long> ordersTo = new ArrayList<>();
        for(Order order : location.getOrdersTo()) {
            ordersTo.add(order.getId());
        }
        return new LocationDto(
                location.getId(),
                location.getLabel(),
                location.getLatitude(),
                location.getLongitude(),
                ordersFrom,
                ordersTo);
    }

    public List<LocationDto> mapToLocationDtoList(final List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<>();
        for(Location location : locations) {
            locationDtos.add(mapToLocationDto(location));
        }
        return locationDtos;
    }
}
