package com.kodilla.backend.controller;

import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.domain.OrderNotFoundException;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.mapper.OrderMapper;
import com.kodilla.backend.order.OrderProcessor;
import com.kodilla.backend.service.DbService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/smart_shipping/")
public class OrdersController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DbService dbService;
    @Autowired
    private OrderProcessor orderProcessor;

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(dbService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(dbService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orders/companyLogin={login}")
    public List<OrderDto> getOrdersByCompanyLogin(@PathVariable String login) {
        return orderMapper.mapToOrderDtoList(dbService.getOrdersByCompanyLogin(login));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        dbService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(dbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderDto orderDto = orderProcessor.createOrder(orderRequestDto);
        return orderMapper.mapToOrderDto(dbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }
}
