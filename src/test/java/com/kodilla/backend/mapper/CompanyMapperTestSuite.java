package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.*;
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
public class CompanyMapperTestSuite {

    @Autowired
    private CompanyMapper mapper;

    @Test
    public void MapToCompanyTest() {
        //Given
        Order order = new Order(12L, "desc", null, new Location(), new Location(), new Driver(), 123.2, "EUR", "Available");
        List<Order> orders = new ArrayList<>();
        CompanyDto companyDto = new CompanyDto(123L, "login", "adsdas", orders);
        orders.add(order);
        companyDto.setOrders(orders);
        //When
        Company company = mapper.mapToCompany(companyDto);
        //Then
        Assert.assertEquals(companyDto.getId(), company.getId());
        Assert.assertEquals(companyDto.getLogin(), company.getLogin());
        Assert.assertEquals(companyDto.getPasswordMD5(), company.getPasswordMd5());
        Assert.assertEquals(companyDto.getOrders(), company.getOrders());
    }
    
    @Test
    public void MapToCompanyDtoTest() {
        //Given
        Order order = new Order(12L, "desc", null, new Location(), new Location(), new Driver(), 123.2, "EUR", "Available");
        List<Order> orders = new ArrayList<>();
        Company company = new Company(123L, "login", "adsdas", orders);
        order.setCompany(company);
        orders.add(order);
        company.setOrders(orders);
        //When
        CompanyDto companyDto = mapper.mapToCompanyDto(company);
        //Then
        Assert.assertEquals(company.getId(), companyDto.getId());
        Assert.assertEquals(company.getLogin(), companyDto.getLogin());
        Assert.assertEquals(company.getPasswordMd5(), companyDto.getPasswordMD5());
        Assert.assertEquals(company.getOrders(), companyDto.getOrders());
    }

    @Test
    public void MapToCompanyListTest() {
        //Given
        Order order = new Order(12L, "desc", null, new Location(), new Location(), new Driver(), 123.2, "EUR", "Available");
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        CompanyDto companyDto = new CompanyDto(123L, "login", "adsdas", orders);
        companyDto.setOrders(orders);
        Order order2 = new Order(123L, "de2sc", null, new Location(), new Location(), new Driver(), 1323.2, "EUR", "Available");
        List<Order> orders2 = new ArrayList<>();
        orders2.add(order2);
        CompanyDto companyDto2 = new CompanyDto(1223L, "logain", "adsddas", orders2);
        companyDto2.setOrders(orders2);
        List<CompanyDto> companyDtoList = new ArrayList<>();
        companyDtoList.add(companyDto);
        companyDtoList.add(companyDto2);
        //When
        List<Company> companyList = mapper.mapToCompanyList(companyDtoList);
        //Then
        Assert.assertEquals(2, companyList.size());
    }

    @Test
    public void MapToCompanyDtoListTest() {
        //Given
        Order order = new Order(12L, "desc", null, new Location(), new Location(), new Driver(), 123.2, "EUR", "Available");
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Company company = new Company(123L, "login", "adsdas", orders);
        company.setOrders(orders);
        Order order2 = new Order(123L, "de2sc", null, new Location(), new Location(), new Driver(), 1323.2, "EUR", "Available");
        List<Order> orders2 = new ArrayList<>();
        orders2.add(order2);
        Company company2 = new Company(1223L, "logain", "adsddas", orders2);
        company2.setOrders(orders2);
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);
        companyList.add(company2);
        //When
        List<CompanyDto> companyDtoList = mapper.mapToCompanyDtoList(companyList);
        //Then
        Assert.assertEquals(2, companyDtoList.size());
    }
}
