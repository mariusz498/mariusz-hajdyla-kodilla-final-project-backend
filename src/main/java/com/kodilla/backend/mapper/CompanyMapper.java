package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.CompanyDto;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.service.DbService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    @Autowired
    private DbService dbService;

    public Company mapToCompany(final CompanyDto companyDto) {
        List<Order> orders = new ArrayList<>();
        for(Long id : companyDto.getOrders()) {
            orders.add(dbService.getOrder(id).orElse(new Order()));
        }
        return new Company(
                companyDto.getId(),
                companyDto.getLogin(),
                companyDto.getPasswordMD5(),
                orders);
    }
    
    public CompanyDto mapToCompanyDto(final Company company) {
        List<Long> orders = new ArrayList<>();
        for(Order order : Optional.ofNullable(company.getOrders()).orElse(new ArrayList<>())) {
            orders.add(order.getId());
        }
        return new CompanyDto(
                company.getId(),
                company.getLogin(),
                company.getPasswordMd5(),
                orders);

    }
    
    public List<CompanyDto> mapToCompanyDtoList(final List<Company> companies) {
        List<CompanyDto> companyDtos = new ArrayList<>();
        for(Company company : companies) {
            companyDtos.add(mapToCompanyDto(company));
        }
        return companyDtos;
    }

    public List<Company> mapToCompanyList(final List<CompanyDto> companyDtos) {
        List<Company> companies = new ArrayList<>();
        for(CompanyDto companyDto : companyDtos) {
            companies.add(mapToCompany(companyDto));
        }
        return companies;
    }
}
