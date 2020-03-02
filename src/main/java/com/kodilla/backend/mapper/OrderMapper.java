package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Order;
import com.kodilla.backend.domain.OrderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
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
}
