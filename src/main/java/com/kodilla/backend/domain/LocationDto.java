package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private String label;
    private Double latitude;
    private Double longitude;
    private List<Long> ordersFrom;
    private List<Long> ordersTo;
}
