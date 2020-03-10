package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequestDto {
    private LocationDto origin;
    private LocationDto destination;
    private Map<String, String> options;
}
