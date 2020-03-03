package com.kodilla.backend.controller;

import com.kodilla.backend.domain.CompanyDto;
import com.kodilla.backend.domain.CompanyNotFoundException;
import com.kodilla.backend.mapper.CompanyMapper;
import com.kodilla.backend.service.DbService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/smart_shipping/")
public class CompanyController {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/companies")
    public List<CompanyDto> getCompanys() {
        return companyMapper.mapToCompanyDtoList(dbService.getAllCompanies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/companies/{companyId}")
    public CompanyDto getCompany(@PathVariable Long companyId) throws CompanyNotFoundException {
        return companyMapper.mapToCompanyDto(dbService.getCompany(companyId).orElseThrow(CompanyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/companys/{companyId}")
    public void deleteCompany(@PathVariable Long companyId){
        dbService.deleteCompany(companyId);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/companies")
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto) {
        return companyMapper.mapToCompanyDto(dbService.saveCompany(companyMapper.mapToCompany(companyDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/companies", consumes = APPLICATION_JSON_VALUE)
    public void createCompany(@RequestBody CompanyDto companyDto) {
        dbService.saveCompany(companyMapper.mapToCompany(companyDto));
    }
}
