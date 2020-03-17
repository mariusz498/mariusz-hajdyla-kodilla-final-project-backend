package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequestDto {
    private CompanyDto company;
    private LocationDto origin;
    private LocationDto destination;
    private Map<String, Boolean> options;
    private String currency;
}
