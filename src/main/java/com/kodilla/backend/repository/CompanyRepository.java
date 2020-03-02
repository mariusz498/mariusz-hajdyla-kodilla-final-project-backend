package com.kodilla.backend.repository;

import com.kodilla.backend.domain.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Override
    List<Company> findAll();

    @Override
    Optional<Company> findById(Long id);

    @Override
    <Company2 extends Company> Company2 save(Company2 company);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
