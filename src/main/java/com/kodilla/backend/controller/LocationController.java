package com.kodilla.backend.controller;

import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.LocationDto;
import com.kodilla.backend.hereApi.client.HereApiClient;
import com.kodilla.backend.hereApi.domain.HereApiLocation;
import com.kodilla.backend.mapper.LocationMapper;
import com.kodilla.backend.service.DbService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/location")
    public LocationDto fetchLocation(@RequestParam("countryCode") String countryCode, @RequestParam("city") String city, @RequestParam("query") String query){
        List<Double> location = hereApiClient.getCityGeocode(city + ", " + countryCode);
        List<HereApiLocation> locationList = hereApiClient.searchLocations(location.get(0), location.get(1), query, countryCode);
        HereApiLocation locationFromApi;
        Location castLocation = new Location();
        if (locationList.isEmpty()) {
            locationFromApi = new HereApiLocation();
        }
        else {
            locationFromApi = locationList.get(0);
            castLocation = new Location(null, locationFromApi.getAddress().getLabel(), locationFromApi.getPosition().getLatitude(), locationFromApi.getPosition().getLongitude(), null, null);
        }
        String label = Optional.ofNullable(castLocation.getLabel()).orElse("");
        Location locationFromDb = dbService.getLocationByLabel(label).orElse(new Location());
        if(locationFromDb.equals(castLocation)) {
            return locationMapper.mapToLocationDto(locationFromDb);
        }
        return locationMapper.mapToLocationDto(castLocation);
    }
}
