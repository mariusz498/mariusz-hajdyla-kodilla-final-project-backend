package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Order;
import com.kodilla.backend.domain.OrderDto;
import org.springframework.stereotype.Component;

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
}
