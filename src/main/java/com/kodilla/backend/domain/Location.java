package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "locations")
@Table(name = "LOCATIONS")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "location_id_seq")
    @NotNull
    @Column(name = "location_id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "label")
    private String label;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;

    @NotNull
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "origin",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Order> ordersFrom;

    @NotNull
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "destination",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Order> ordersTo;
}
