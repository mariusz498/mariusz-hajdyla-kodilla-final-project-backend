package com.kodilla.backend.controller;


import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.mapper.OrderMapper;
import com.kodilla.backend.service.DbService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/smart_shipping/")
public class OrdersController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(dbService.getAllOrders());
    }
}
