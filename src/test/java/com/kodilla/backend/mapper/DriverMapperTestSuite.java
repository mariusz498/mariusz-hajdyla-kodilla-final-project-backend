package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.DriverDto;
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
public class DriverMapperTestSuite {

    @Autowired
    private DriverMapper mapper;

    @Test
    public void MapToDriverTest() {
        //Given
        DriverDto driverDto = new DriverDto(123L, "driver", "password", new ArrayList<>());
        //When
        Driver driver = mapper.mapToDriver(driverDto);
        //Then
        Assert.assertEquals(driver.getId(), driverDto.getId());
        Assert.assertEquals(driver.getLogin(), driverDto.getLogin());
        Assert.assertEquals(driver.getPasswordMd5(), driverDto.getPasswordMD5());
        Assert.assertEquals(driver.getOrders(), driverDto.getOrders());
    }


    @Test
    public void MapToDriverDtoTest() {
        //Given
        Driver driver = new Driver(123L, "driver", "password", new ArrayList<>());
        //When
        DriverDto driverDto = mapper.mapToDriverDto(driver);
        //Then
        Assert.assertEquals(driver.getId(), driverDto.getId());
        Assert.assertEquals(driver.getLogin(), driverDto.getLogin());
        Assert.assertEquals(driver.getPasswordMd5(), driverDto.getPasswordMD5());
        Assert.assertEquals(driver.getOrders(), driverDto.getOrders());
    }

    @Test
    public void MapToDriverDtoListTest() {
        //Given
        Driver driver = new Driver(123L, "driver", "password", new ArrayList<>());
        Driver driver2 = new Driver(1223L, "driver2", "password2", new ArrayList<>());
        List<Driver> list = new ArrayList<>();
        list.add(driver);
        list.add(driver2);
        //When
        List<DriverDto> resultList = mapper.mapToDriverDtoList(list);
        //Then
        Assert.assertEquals(2, resultList.size());
    }
}
