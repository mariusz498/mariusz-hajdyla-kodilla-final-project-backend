package com.kodilla.backend.repository;

import com.kodilla.backend.domain.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    @Override
    List<Driver> findAll();

    @Override
    Optional<Driver> findById(Long id);

    @Override
    <Driver2 extends Driver> Driver2 save(Driver2 driver);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
