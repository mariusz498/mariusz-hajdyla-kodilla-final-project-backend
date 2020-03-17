package com.kodilla.backend.mapper;

import com.kodilla.backend.controller.CompanyController;
import com.kodilla.backend.domain.*;
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
    private CompanyMapper companyMapper;

    @Mock
    private CompanyController companyController;


    @Test
    public void MapToOrderTest() {
        //Given
        OrderDto orderDto = new OrderDto(123L, "order", "company", new Location(), new Location(), new Driver(), 123.4, "EUR", "Available");
        Company company = new Company(123L, "company", "pass", new ArrayList<>());
        when(companyMapper.mapToCompany(companyController.getCompanyByLogin("company"))).thenReturn(company);
        //When
        Order order = mapper.mapToOrder(orderDto);
        //Then
        Assert.assertEquals(order.getId(), orderDto.getId());
        Assert.assertEquals(order.getDescription(), orderDto.getDescription());
        Assert.assertEquals(order.getCompany(), company);
        Assert.assertEquals(order.getOrigin(), orderDto.getOrigin());
        Assert.assertEquals(order.getDestination(), orderDto.getDestination());
        Assert.assertEquals(order.getDriver(), orderDto.getDriver());
        Assert.assertEquals(order.getValue(), orderDto.getValue());
        Assert.assertEquals(order.getCurrency(), orderDto.getCurrency());
        Assert.assertEquals(order.getStatus(), orderDto.getStatus());
    }


    @Test
    public void MapToOrderDtoTest() {
        //Given
        Company company = new Company(123L, "company", "pass", new ArrayList<>());
        Order order = new Order(123L, "order", company, new Location(), new Location(), new Driver(), 123.4, "EUR", "Available");
        //When
        OrderDto orderDto = mapper.mapToOrderDto(order);
        //Then
        Assert.assertEquals(order.getId(), orderDto.getId());
        Assert.assertEquals(order.getDescription(), orderDto.getDescription());
        Assert.assertEquals("company", orderDto.getCompany());
        Assert.assertEquals(order.getOrigin(), orderDto.getOrigin());
        Assert.assertEquals(order.getDestination(), orderDto.getDestination());
        Assert.assertEquals(order.getDriver(), orderDto.getDriver());
        Assert.assertEquals(order.getValue(), orderDto.getValue());
        Assert.assertEquals(order.getCurrency(), orderDto.getCurrency());
        Assert.assertEquals(order.getStatus(), orderDto.getStatus());
    }

    @Test
    public void MapToOrderDtoListTest() {
        //Given
        Company company = new Company(123L, "company", "pass", new ArrayList<>());
        Order order = new Order(123L, "order", company, new Location(), new Location(), new Driver(), 123.4, "EUR", "Available");
        Order order2 = new Order(1232L, "orde2r", company, new Location(), new Location(), new Driver(), 123.4, "EUR", "Available");
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        //When
        List<OrderDto> result = mapper.mapToOrderDtoList(orderList);

        //Then
        Assert.assertEquals(2, result.size());
    }
}
