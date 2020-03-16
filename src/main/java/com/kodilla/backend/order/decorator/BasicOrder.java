package com.kodilla.backend.order.decorator;

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
        return new Double(100.00 + 0.6 * 0.001*distance);
    }
    @Override
    public String getDescription() {
        return "Land transport";
    }
}
