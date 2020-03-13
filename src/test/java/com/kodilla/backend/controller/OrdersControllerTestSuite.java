package com.kodilla.backend.controller;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.repository.CompanyRepository;
import com.kodilla.backend.repository.OrderRepository;
import com.kodilla.backend.service.DbService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTestSuite {

    @InjectMocks
    private DbService service;

    @Mock
    private OrderRepository repository;

    @Mock
    CompanyRepository companyRepository;

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
}
