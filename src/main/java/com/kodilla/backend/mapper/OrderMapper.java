package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    @Autowired
    private DbService dbService;

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getDescription(),
                dbService.getCompanyByLogin(orderDto.getCompany()).orElse(new Company()),
                dbService.getLocation(orderDto.getOrigin()),
                dbService.getLocation(orderDto.getDestination()),
                dbService.getDriverByLogin(orderDto.getDriver()).orElse(new Driver()),
                orderDto.getValue(),
                orderDto.getCurrency(),
                orderDto.getStatus());
    }
    
    public OrderDto mapToOrderDto(final Order order) {
        String login = order.getDriver().getLogin();

        return new OrderDto(
                order.getId(),
                order.getDescription(),
                order.getCompany().getLogin(),
                order.getOrigin().getId(),
                order.getDestination().getId(),
                login,
                order.getValue(),
                order.getCurrency(),
                order.getStatus());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
       for(Order order : orders) {
           orderDtos.add(mapToOrderDto(order));
       }
       return orderDtos;
    }

    public OrderDto mapToCreatedOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getDescription(),
                order.getCompany().getLogin(),
                order.getOrigin().getId(),
                order.getDestination().getId(),
                "",
                order.getValue(),
                order.getCurrency(),
                order.getStatus());
    }
}
