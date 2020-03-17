package com.kodilla.backend.service;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.repository.CompanyRepository;
import com.kodilla.backend.repository.DriverRepository;
import com.kodilla.backend.repository.LocationRepository;
import com.kodilla.backend.repository.OrderRepository;
import java.util.Optional;
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
    private OrderRepository orderRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    DriverRepository driverRepository;

    @Mock
    LocationRepository locationRepository;

    @Test
    public void getAllOrdersTest(){
        //Given
        List<Order> orderList = new ArrayList<>();
        Company company = new Company();
        Location location = new Location();
        orderList.add(new Order(1234L, "name", company, location, location, null, 1200.0, "EUR", "Available"));
        orderList.add(new Order(4231L, "name2", company, location, location, null, 12300.0, "GBP", "Available"));
        when(orderRepository.findAll()).thenReturn(orderList);
        //When
        List<Order> resultList = service.getAllOrders();
        //Then
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void getOrderTest(){
        //Given
        Company company = new Company();
        Location location = new Location();
        Order order = (new Order(1234L, "name", company, location, location, null, 1200.0, "EUR", "Available"));
        when(orderRepository.findById(1234L)).thenReturn(java.util.Optional.of(order));
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
        when(orderRepository.findAllByCompany(company)).thenReturn(orderList);
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
        when(orderRepository.save(order)).thenReturn(order);
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

    @Test
    public void getAllCompaniesTest() {
        //Given
        List<Company> list = new ArrayList<>();
        Company company = new Company(123L, "mycompany", "1234565", new ArrayList<>());
        Company company2 = new Company(1223L, "mycompany2", "12324565", new ArrayList<>());
        list.add(company);
        list.add(company2);
        when(companyRepository.findAll()).thenReturn(list);
        //When
        List<Company> resultList = service.getAllCompanies();
        //Then
        Assert.assertEquals(2, resultList.size());
        Assert.assertEquals("mycompany", resultList.get(0).getLogin());
        Assert.assertEquals("mycompany2", resultList.get(1).getLogin());
    }

    @Test
    public void getCompanyTest() {
        //Given
        Company company = new Company(123L, "mycompany", "1234565", new ArrayList<>());
        when(companyRepository.findById(123L)).thenReturn(java.util.Optional.of(company));
        //When
        Company result = service.getCompany(123L).orElse(new Company());
        //Then
        Assert.assertEquals(123L, (long)result.getId());
    }

    @Test
    public void getCompanyByLoginTest() {
        //Given
        Company company = new Company(123L, "mycompany", "1234565", new ArrayList<>());
        when(companyRepository.findByLogin("mycompany")).thenReturn(Optional.of(company));
        //When
        Company result = service.getCompanyByLogin("mycompany").orElse(new Company());
        //Then
        Assert.assertEquals("mycompany", result.getLogin());
        Assert.assertEquals(0, result.getOrders().size());
        Assert.assertEquals("1234565", result.getPasswordMd5());
    }

    @Test
    public void saveCompanyTest() {
        //Given
        Company company = new Company(123L, "mycompany", "1234565", new ArrayList<>());
        when(companyRepository.save(company)).thenReturn(company);
        //When
        Company result = service.saveCompany(company);
        //Then
        Assert.assertEquals("mycompany", result.getLogin());
        Assert.assertEquals(0, result.getOrders().size());
        Assert.assertEquals("1234565", result.getPasswordMd5());
    }

    @Test
    public void getAllDriversTest() {
        //Given
        List<Driver> list = new ArrayList<>();
        Driver driver = new Driver(123L, "driver", "12345", new ArrayList<>());
        Driver driver2 = new Driver(122133L, "driver2", "1232345", new ArrayList<>());
        list.add(driver);
        list.add(driver2);
        when(driverRepository.findAll()).thenReturn(list);
        //When
        List<Driver> resultList = service.getAllDrivers();
        //Then
        Assert.assertEquals(2, resultList.size());
        Assert.assertEquals("driver", resultList.get(0).getLogin());
        Assert.assertEquals("driver2", resultList.get(1).getLogin());
    }

    @Test
    public void getDriverTest() {
        //Given
        Driver driver = new Driver(123L, "driver", "12345", new ArrayList<>());
        when(driverRepository.findById(123L)).thenReturn(Optional.of(driver));
        //When
        Driver result = service.getDriver(123L).orElse(new Driver());
        //Then
        Assert.assertEquals(123L, (long)result.getId());
    }

    @Test
    public void getDriverByLoginTest() {
        //Given
        Driver driver = new Driver(123L, "driver", "12345", new ArrayList<>());
        when(driverRepository.findByLogin("driver")).thenReturn(Optional.of(driver));
        //When
        Driver result = service.getDriverByLogin("driver").orElse(new Driver());
        //Then
        Assert.assertEquals(123L, (long)result.getId());
    }

    @Test
    public void saveDriverTest() {
        //Given
        Driver driver = new Driver(123L, "driver", "12345", new ArrayList<>());
        when(driverRepository.save(driver)).thenReturn(driver);
        //When
        Driver result = service.saveDriver(driver);
        //Then
        Assert.assertEquals(123L, (long)result.getId());
        Assert.assertEquals("driver", result.getLogin());
        Assert.assertEquals("12345", result.getPasswordMd5());
        Assert.assertEquals(0, result.getOrders().size());
    }

    @Test
    public void getAllLocationsTest() {
        //Given
        List<Location> list = new ArrayList<>();
        Location location = new Location(123L, "label", 123.4, 234.5, new ArrayList<>(), new ArrayList<>());
        Location location2 = new Location(1234L, "label2", 1223.4, 2134.5, new ArrayList<>(), new ArrayList<>());
        list.add(location);
        list.add(location2);
        when(locationRepository.findAll()).thenReturn(list);
        //When
        List<Location> resultList = service.getAllLocations();
        //Then
        Assert.assertEquals(2, resultList.size());
        Assert.assertEquals("label", resultList.get(0).getLabel());
        Assert.assertEquals("label2", resultList.get(1).getLabel());
    }

    @Test
    public void getLocationTest() {
        //Given
        Location location = new Location(123L, "label", 123.4, 234.5, new ArrayList<>(), new ArrayList<>());
        when(locationRepository.findById(123L)).thenReturn(Optional.of(location));
        //When
        Location result = service.getLocation(123L).orElse(new Location());
        //Then
        Assert.assertEquals("label", result.getLabel());
    }

    @Test
    public void getAllLocationByLabelTest() {
        //Given
        Location location = new Location(123L, "label", 123.4, 234.5, new ArrayList<>(), new ArrayList<>());
        when(locationRepository.findByLabel("label")).thenReturn(Optional.of(location));
        //When
        Location result = service.getLocationByLabel("label").orElse(new Location());
        //Then
        Assert.assertEquals(123.4, result.getLatitude(), 0.01);
    }

    @Test
    public void saveLocationTest() {
        //Given
        Location location = new Location(123L, "label", 123.4, 234.5, new ArrayList<>(), new ArrayList<>());
        when(locationRepository.save(location)).thenReturn(location);
        //When
        Location result = service.saveLocation(location);
        //Then
        Assert.assertEquals(123L, (long)result.getId());
        Assert.assertEquals("label", result.getLabel());
        Assert.assertEquals(123.4, result.getLatitude(), 0.01);
        Assert.assertEquals(234.5, result.getLongitude(), 0.01);
        Assert.assertEquals(new ArrayList<Order>(), result.getOrdersFrom());
        Assert.assertEquals(new ArrayList<Order>(), result.getOrdersTo());
    }
}
