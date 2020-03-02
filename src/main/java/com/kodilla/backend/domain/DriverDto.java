package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DriverDto {
    private Long id;
    private String login;
    private String passwordMD5;
    private List<Order> orders;
}
