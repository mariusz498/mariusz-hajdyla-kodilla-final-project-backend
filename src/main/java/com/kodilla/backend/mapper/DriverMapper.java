package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.DriverDto;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.service.DbService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    @Autowired
    private DbService dbService;

    public Driver mapToDriver(final DriverDto driverDto) {
        List<Order> orders = new ArrayList<>();
        for(Long id : driverDto.getOrders()) {
            orders.add(dbService.getOrder(id).orElse(new Order()));
        }
        return new Driver(
                driverDto.getId(),
                driverDto.getLogin(),
                driverDto.getPasswordMD5(),
                orders);
    }

    public DriverDto mapToDriverDto(final Driver driver) {
        List<Long> orders = new ArrayList<>();
        for(Order order : driver.getOrders()) {
            orders.add(order.getId());
        }
        return new DriverDto(
                driver.getId(),
                driver.getLogin(),
                driver.getPasswordMd5(),
                orders);
    }

    public List<DriverDto> mapToDriverDtoList(final List<Driver> drivers) {
        List<DriverDto> driverDtos = new ArrayList<>();
        for(Driver driver : drivers) {
            driverDtos.add(mapToDriverDto(driver));
        }
        return driverDtos;
    }
}
