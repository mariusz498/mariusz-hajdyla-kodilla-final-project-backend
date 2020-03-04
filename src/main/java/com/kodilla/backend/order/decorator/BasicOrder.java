package com.kodilla.backend.order.decorator;

public class BasicOrder implements OrderInterface {
    private Double distance;

    public BasicOrder(Double distance) {
        this.distance = distance;
    }

    @Override
    public Double getDistance() {
        return distance;
    }
    @Override
    public Double getCost() {
        return new Double(100.00 + 0.6 * distance);
    }

    @Override
    public String getDescription() {
        return "Land transport";
    }
}
