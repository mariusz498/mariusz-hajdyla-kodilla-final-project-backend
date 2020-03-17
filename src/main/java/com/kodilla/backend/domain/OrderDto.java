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
    private String company;
    private Long origin;
    private Long destination;
    private String driver;
    private Double value;
    private String currency;
    private String status;
}
