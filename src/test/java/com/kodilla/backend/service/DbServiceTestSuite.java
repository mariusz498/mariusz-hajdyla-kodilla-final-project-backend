package com.kodilla.backend.service;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.repository.CompanyRepository;
import com.kodilla.backend.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService service;

    @Mock
    private OrderRepository repository;

    @Mock
    CompanyRepository companyRepository;

    @Test
    public void getAllOrdersTest(){
        //Given
        List<Order> orderList = new ArrayList<>();
        Company company = new Company();
        Location location = new Location();
        orderList.add(new Order(1234L, "name", company, location, location, null, 1200.0, "EUR", "Available"));
        orderList.add(new Order(4231L, "name2", company, location, location, null, 12300.0, "GBP", "Available"));
        when(repository.findAll()).thenReturn(orderList);
        //When
        List<Order> resultList = service.getAllOrders();
        //Then
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void getOrderByIdTest(){
        //Given
        List<Order> orderList = new ArrayList<>();
        Company company = new Company();
        Location location = new Location();
        Order order = (new Order(1234L, "name", company, location, location, null, 1200.0, "EUR", "Available"));
        when(repository.findById(1234L)).thenReturn(java.util.Optional.of(order));
        //When
        Order result = (service.getOrder(1234L)).orElse(new Order());
        //Then
        Assert.assertEquals(1234L, (long)result.getId());
    }

    @Test
    public void getOrdersByCompanyLoginTest() {
        //Given
        List<Order> orderList = new ArrayList<>();
        Company company = new Company(12L, "login", "1234", null);
        Location location = new Location();
        orderList.add(new Order(1234L, "name", company, location, location, null, 1200.0, "EUR", "available"));
        when(repository.findAllByCompany(company)).thenReturn(orderList);
        when(companyRepository.findByLogin(company.getLogin())).thenReturn(java.util.Optional.of(company));
        //When
        List<Order> resultList = service.getOrdersByCompanyLogin(company.getLogin());
        //Then
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void saveOrderTest(){
        //Given
        Company company = new Company();
        Location location = new Location();
        Driver driver = new Driver();
        Order order = (new Order(1234L, "name", company, location, location, driver, 1200.0, "EUR", "Available"));
        when(repository.save(order)).thenReturn(order);
        //When
        Order result = service.saveOrder(order);
        //Then
        Assert.assertTrue(result.getDescription().equals(order.getDescription()));
        Assert.assertTrue(result.getId().equals(order.getId()));
        Assert.assertTrue(result.getCompany().equals(order.getCompany()));
        Assert.assertTrue(result.getOrigin().equals(order.getOrigin()));
        Assert.assertTrue(result.getDestination().equals(order.getDestination()));
        Assert.assertTrue(result.getDriver().equals(order.getDriver()));
        Assert.assertTrue(result.getValue().equals(order.getValue()));
        Assert.assertTrue(result.getCurrency().equals(order.getCurrency()));
        Assert.assertTrue(result.getStatus().equals(order.getStatus()));
    }
}
