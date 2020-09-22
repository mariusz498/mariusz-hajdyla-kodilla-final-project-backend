package com.kodilla.backend.order.decorator;

import org.apache.commons.math3.util.Precision;

public class BasicOrder implements OrderInterface {
    private final Integer distance;

    public BasicOrder(Integer distance) {
        this.distance = distance;
    }

    @Override
    public Integer getDistance() {
        return distance;
    }
    @Override
    public Double getCost() {
        double value = 100.00 + 0.6 * 0.001 * distance;
        return Precision.round(value, 2);
    }
    @Override
    public String getDescription() {
        return "Land transport";
    }
}
