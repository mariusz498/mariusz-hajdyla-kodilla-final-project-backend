package com.kodilla.backend.repository;


import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    List<Order> findAllByCompany(Company company);

    @Override
    Optional<Order> findById(Long id);

    @Override
    <Order2 extends Order> Order2 save(Order2 order);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}