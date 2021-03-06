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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "drivers")
@Table(name = "DRIVERS")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "driver_id_seq")
    @NotNull
    @Column(name = "deliverer_id", unique = true)
    private Long id;

    @Column(name = "deliverer_login", unique = true)
    private String login;

    @Column(name = "deliverer_password_MD5")
    private String passwordMd5;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Order> orders;
}
