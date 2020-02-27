package com.kodilla.backend.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company")
    private Long companyId;

    @Column(name = "from")
    private Long originId;

    @Column(name = "to")
    private Long destinationId;

    @Column(name = "driver")
    private Long driverId;

    @Column(name = "status")
    private String status;
}
