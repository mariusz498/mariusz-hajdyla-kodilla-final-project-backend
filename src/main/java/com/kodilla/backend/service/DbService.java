package com.kodilla.backend.service;

import com.kodilla.backend.domain.Order;
import com.kodilla.backend.repository.OrderRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return repository.findById(id);
    }

    public Order saveOrder(final Order order) {
        return repository.save(order);
    }

    public void deleteOrder(Long id){
        repository.deleteById(id);
    }
}
