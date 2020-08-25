package com.kodilla.backend.controller;

import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.LocationDto;
import com.kodilla.backend.hereApi.client.HereApiClient;
import com.kodilla.backend.hereApi.domain.HereApiLocation;
import com.kodilla.backend.mapper.LocationMapper;
import com.kodilla.backend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/smart_shipping/")
public class LocationController {

    @Autowired
    private HereApiClient hereApiClient;

    @Autowired
    private DbService dbService;

    @Autowired
    private LocationMapper locationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/locations")
    public List<LocationDto> getLocations() {
        return locationMapper.mapToLocationDtoList(dbService.getAllLocations());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/locations/id={id}")
    public LocationDto getLocationById(@PathVariable Long id) {
        return locationMapper.mapToLocationDto(dbService.getLocation(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/location")
    public LocationDto fetchLocation(@RequestParam("code") String code, @RequestParam("city") String city, @RequestParam("query") String query){
        List<Double> location = hereApiClient.getCityGeocode(code + "," + city);
        List<HereApiLocation> locationList = hereApiClient.searchLocations(location.get(0), location.get(1), query, code);
        HereApiLocation locationFromApi;
        if (locationList.isEmpty()) {
            return new LocationDto();
        }
        else {
            locationFromApi = locationList.get(0);
            Location castLocation = new Location(null, locationFromApi.getAddress().getLabel(), locationFromApi.getPosition().getLatitude(),
                    locationFromApi.getPosition().getLongitude(), new ArrayList<>(), new ArrayList<>());
            String label = Optional.ofNullable(castLocation.getLabel()).orElse("");
            Location locationFromDb = dbService.getLocationByLabel(label).orElse(new Location());
            if (locationFromDb.equals(castLocation)) {
                return locationMapper.mapToLocationDto(locationFromDb);
            }
        return locationMapper.mapToLocationDto(castLocation);
        }
    }
}
