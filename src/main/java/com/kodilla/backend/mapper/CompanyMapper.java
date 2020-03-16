package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.CompanyDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    @Autowired
    private OrderMapper orderMapper;

    public Company mapToCompany(final CompanyDto companyDto) {
        return new Company(
                companyDto.getId(),
                companyDto.getLogin(),
                companyDto.getPasswordMD5(),
                orderMapper.mapToOrderList(companyDto.getOrders()));
    }
    
    public CompanyDto mapToCompanyDto(final Company company) {
        return new CompanyDto(
                company.getId(),
                company.getLogin(),
                company.getPasswordMd5(),
                orderMapper.mapToOrderDtoList(company.getOrders()));
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
