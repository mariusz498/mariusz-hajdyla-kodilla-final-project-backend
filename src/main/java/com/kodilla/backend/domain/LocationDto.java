package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LocationDto {
    private Long id;
    private String name;
    private Double lattitude;
    private Double longitude;
    private List<Order> ordersFrom;
    private List<Order> ordersTo;
}
