package com.kodilla.backend.mapper;

import com.kodilla.backend.controller.CompanyController;
import com.kodilla.backend.domain.*;
import com.kodilla.backend.service.DbService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestSuite {

    @InjectMocks
    private OrderMapper mapper;

    @Mock
    private DbService dbService;


    @Test
    public void MapToOrderTest() {
        //Given
        Location origin = new Location();
        origin.setId(1L);
        Driver driver = new Driver();
        driver.setLogin("franek");
        OrderDto orderDto = new OrderDto(123L, "order", "company", 1L, 1L, "franek", 123.4, "EUR", "Available");
        Company company = new Company(123L, "company", "pass", new ArrayList<>());
        when(dbService.getCompanyByLogin("company")).thenReturn(java.util.Optional.of(company));
        when(dbService.getLocation(orderDto.getOrigin())).thenReturn(origin);
        when(dbService.getLocation(orderDto.getDestination())).thenReturn(origin);
        when(dbService.getDriverByLogin(orderDto.getDriver())).thenReturn(java.util.Optional.of(driver));
        //When
        Order order = mapper.mapToOrder(orderDto);
        //Then
        Assert.assertEquals(order.getId(), orderDto.getId());
        Assert.assertEquals(order.getDescription(), orderDto.getDescription());
        Assert.assertEquals(order.getCompany(), company);
        Assert.assertEquals(origin, order.getOrigin());
        Assert.assertEquals(origin, order.getDestination());
        Assert.assertEquals(driver, order.getDriver());
        Assert.assertEquals(order.getValue(), orderDto.getValue());
        Assert.assertEquals(order.getCurrency(), orderDto.getCurrency());
        Assert.assertEquals(order.getStatus(), orderDto.getStatus());
    }


    @Test
    public void MapToOrderDtoTest() {
        //Given
        Company company = new Company(123L, "company", "pass", new ArrayList<>());
        Location origin = new Location();
        origin.setId(12L);
        Location destination = new Location();
        destination.setId(122L);
        Driver driver = new Driver();
        driver.setLogin("franek");
        Order order = new Order(123L, "order", company, origin, destination, driver, 123.4, "EUR", "Available");
        //When
        OrderDto orderDto = mapper.mapToOrderDto(order);
        //Then
        Assert.assertEquals(order.getId(), orderDto.getId());
        Assert.assertEquals(order.getDescription(), orderDto.getDescription());
        Assert.assertEquals("company", orderDto.getCompany());
        Assert.assertEquals(12L, (long)orderDto.getOrigin());
        Assert.assertEquals(122L, (long)orderDto.getDestination());
        Assert.assertEquals(order.getDriver().getLogin(), orderDto.getDriver());
        Assert.assertEquals(order.getValue(), orderDto.getValue());
        Assert.assertEquals(order.getCurrency(), orderDto.getCurrency());
        Assert.assertEquals(order.getStatus(), orderDto.getStatus());
    }

    @Test
    public void MapToOrderDtoListTest() {
        //Given
        Location origin = new Location();
        origin.setId(12L);
        Location destination = new Location();
        origin.setId(122L);
        Driver driver = new Driver();
        driver.setLogin("franek");
        Company company = new Company(123L, "company", "pass", new ArrayList<>());
        Order order = new Order(123L, "order", company, origin, destination, driver, 123.4, "EUR", "Available");
        Order order2 = new Order(1232L, "orde2r", company, origin, destination, driver, 123.4, "EUR", "Available");
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        //When
        List<OrderDto> result = mapper.mapToOrderDtoList(orderList);

        //Then
        Assert.assertEquals(2, result.size());
    }
}
