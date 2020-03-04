package com.kodilla.backend.order.decorator;


import java.math.BigDecimal;

public class BasicOrder implements OrderInterface {
    @Override
    public Double getCost() {
        return new Double(100.00);
    }

    @Override
    public String getDescription() {
        return "Land transport";
    }
}
