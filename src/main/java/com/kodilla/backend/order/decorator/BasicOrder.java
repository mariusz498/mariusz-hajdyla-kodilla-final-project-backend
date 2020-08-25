package com.kodilla.backend.order.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        Double value = 100.00 + 0.6 * 0.001 * distance;
        return BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
    @Override
    public String getDescription() {
        return "Land transport";
    }
}
