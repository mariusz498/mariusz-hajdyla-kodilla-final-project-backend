package com.kodilla.backend.order.decorator;

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
        DecimalFormat dec = new DecimalFormat("#0.00");
        Double result = (new Double(dec.format(new Double(100.00 + 0.6 * 0.001 * distance))));
        return result;
    }
    @Override
    public String getDescription() {
        return "Land transport";
    }
}
