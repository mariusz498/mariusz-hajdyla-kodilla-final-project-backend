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
    private String label;
    private Double latitude;
    private Double longitude;
    private List<OrderDto> ordersFrom;
    private List<OrderDto> ordersTo;
}
