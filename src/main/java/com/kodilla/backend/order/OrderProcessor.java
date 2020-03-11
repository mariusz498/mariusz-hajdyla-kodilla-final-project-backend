package com.kodilla.backend.order;

import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.hereApi.client.HereApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    @Autowired
    HereApiClient hereApiClient;

    public OrderDto createOrder(OrderRequestDto request) {
//TODO make processor
        return new OrderDto();
    }
}
