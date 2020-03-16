package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Order;
import com.kodilla.backend.domain.OrderDto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    
    @Autowired
    private CompanyMapper companyMapper;
    
    @Autowired
    private LocationMapper locationMapper;
    
    @Autowired
    private DriverMapper driverMapper;
    
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getDescription(),
                orderDto.getCompany(),
                orderDto.getOrigin(),
                orderDto.getDestination(),
                orderDto.getDriver(),
                orderDto.getValue(),
                orderDto.getCurrency(),
                orderDto.getStatus());
    }
    
    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getDescription(),
                order.getCompany(),
                order.getOrigin(),
                order.getDestination(),
                order.getDriver(),
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

    public List<Order> mapToOrderList(final List<OrderDto> orderDtos) {
        List<Order> orders = new ArrayList<>();
        for(OrderDto orderDto : orderDtos) {
            orders.add(mapToOrder(orderDto));
        }
        return orders;
    }

    public OrderDto mapToCreatedOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getDescription(),
                order.getCompany(),
                order.getOrigin(),
                order.getDestination(),
                null,
                order.getValue(),
                order.getCurrency(),
                order.getStatus());
    }
}
