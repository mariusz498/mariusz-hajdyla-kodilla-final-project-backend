package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long companyId;
    private Long originId;
    private Long destinationId;
    private Long driverId;
    private String status;
}
