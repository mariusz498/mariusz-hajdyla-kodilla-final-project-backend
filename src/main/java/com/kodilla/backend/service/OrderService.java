package com.kodilla.backend.service;

import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.mapper.OrderMapper;
import com.kodilla.backend.order.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private DbService dbService;

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private OrderMapper orderMapper;
    //TODO createOrder and make an entity, return Order to frontend

    public void processNewOrder(OrderRequestDto request) {
        OrderDto createdOrderDto = orderProcessor.createOrder(request);
        dbService.saveOrder(orderMapper.mapToOrder(createdOrderDto));
    }
}
