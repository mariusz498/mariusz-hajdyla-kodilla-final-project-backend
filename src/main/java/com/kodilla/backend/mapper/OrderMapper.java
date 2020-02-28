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
                orderDto.getCompanyId(),
                orderDto.getOriginId(),
                orderDto.getDestinationId(),
                orderDto.getDriverId(),
                orderDto.getStatus());
    }
    
    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getCompanyId(),
                order.getOriginId(),
                order.getDestinationId(),
                order.getDriverId(),
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
