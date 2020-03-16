package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.DriverDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public Driver mapToDriver(final DriverDto driverDto) {
        return new Driver(
                driverDto.getId(),
                driverDto.getLogin(),
                driverDto.getPasswordMD5(),
                driverDto.getOrders());
    }

    public DriverDto mapToDriverDto(final Driver driver) {
        return new DriverDto(
                driver.getId(),
                driver.getLogin(),
                driver.getPasswordMd5(),
                driver.getOrders());
    }

    public List<DriverDto> mapToDriverDtoList(final List<Driver> drivers) {
        List<DriverDto> driverDtos = new ArrayList<>();
        for(Driver driver : drivers) {
            driverDtos.add(mapToDriverDto(driver));
        }
        return driverDtos;
    }

    public List<Driver> mapToDriverList(final List<DriverDto> driverDtos) {
        List<Driver> drivers = new ArrayList<>();
        for(DriverDto driverDto : driverDtos) {
            drivers.add(mapToDriver(driverDto));
        }
        return drivers;
    }
}
