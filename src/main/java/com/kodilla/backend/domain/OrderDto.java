package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String description;
    private CompanyDto company;
    private LocationDto origin;
    private LocationDto destination;
    private DriverDto driver;
    private Double value;
    private String currency;
    private String status;
}
