package com.kodilla.backend.order.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BasicOrder implements OrderInterface {
    private Integer distance;

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
        Double result = BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).doubleValue();
        return result;
    }
    @Override
    public String getDescription() {
        return "Land transport";
    }
}
