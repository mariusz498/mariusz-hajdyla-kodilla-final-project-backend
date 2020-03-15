package com.kodilla.backend.repository;

import com.kodilla.backend.domain.Location;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {
    @Override
    List<Location> findAll();

    @Override
    Optional<Location> findById(Long id);

    Optional<Location> findByLabel(String label);

    @Override
    <Location2 extends Location> Location2 save(Location2 location);

    @Override
    void deleteById(Long id);

    @Override
    long count();

}