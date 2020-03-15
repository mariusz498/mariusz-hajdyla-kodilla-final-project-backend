package com.kodilla.backend.service;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.Location;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.repository.CompanyRepository;
import com.kodilla.backend.repository.DriverRepository;
import com.kodilla.backend.repository.LocationRepository;
import com.kodilla.backend.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CompanyRepository companyRepository;
    

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }
    public List<Order> getOrdersByCompanyLogin(String login) {
        Company company = companyRepository.findByLogin(login).orElse(new Company());
        List<Order> orders = new ArrayList<>();
        if(company.getLogin() != null) {
            orders = orderRepository.findAllByCompany(company);
        }
        return orders;
    }
    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    public Optional<Driver> getDriver(Long id) {
        return driverRepository.findById(id);
    }
    public Optional<Driver> getDriverByLogin(String login) {
        return driverRepository.findByLogin(login);
    }
    public Driver saveDriver(final Driver driver) {
        return driverRepository.save(driver);
    }
    public void deleteDriver(Long id){
        driverRepository.deleteById(id);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public Optional<Location> getLocation(Long id) {
        return locationRepository.findById(id);
    }
    public Optional<Location> getLocationByLabel(String label) {
        return locationRepository.findByLabel(label);
    }

    public Location saveLocation(final Location location) {
        return locationRepository.save(location);
    }
    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Optional<Company> getCompany(Long id) {
        return companyRepository.findById(id);
    }
    public Optional<Company> getCompanyByLogin(String login) {
        return companyRepository.findByLogin(login);
    }
    public Company saveCompany(final Company company) {
        return companyRepository.save(company);
    }
    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }
}
