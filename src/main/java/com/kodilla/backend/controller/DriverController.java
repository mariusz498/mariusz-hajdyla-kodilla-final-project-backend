package com.kodilla.backend.controller;

import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.DriverDto;
import com.kodilla.backend.domain.DriverNotFoundException;
import com.kodilla.backend.mapper.DriverMapper;
import com.kodilla.backend.service.DbService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/smart_shipping/")
public class DriverController {
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/drivers")
    public List<DriverDto> getDrivers() {
        return driverMapper.mapToDriverDtoList(dbService.getAllDrivers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drivers/id={driverId}")
    public DriverDto getDriver(@PathVariable Long driverId) throws DriverNotFoundException {
        return driverMapper.mapToDriverDto(dbService.getDriver(driverId).orElseThrow(DriverNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drivers/login={login}")
    public DriverDto getDriverByLogin(@PathVariable String login) {
        return driverMapper.mapToDriverDto(dbService.getDriverByLogin(login).orElse(new Driver()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/drivers/{driverId}")
    public void deleteDriver(@PathVariable Long driverId){
        dbService.deleteDriver(driverId);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/drivers")
    public DriverDto updateDriver(@RequestBody DriverDto driverDto) {
        return driverMapper.mapToDriverDto(dbService.saveDriver(driverMapper.mapToDriver(driverDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/drivers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DriverDto createDriver(@RequestBody DriverDto driverDto) {
        return driverMapper.mapToDriverDto(dbService.saveDriver(driverMapper.mapToDriver(driverDto)));
    }
}