package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String description;
    private Company company;
    private Location origin;
    private Location destination;
    private Driver driver;
    private Double value;
    private String currency;
    private String status;
}
