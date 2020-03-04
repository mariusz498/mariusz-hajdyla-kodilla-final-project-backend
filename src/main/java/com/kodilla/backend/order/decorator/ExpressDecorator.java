package com.kodilla.backend.order.decorator;

public class ExpressDecorator extends AbstractOrderDecorator {
    private Double distance;
    public ExpressDecorator(OrderInterface theOrder, Double distance) {
        super(theOrder);
        this.distance = distance;
    }

    @Override
    public Double getCost() {
        return super.getCost() + (new Double(distance * 1.2));
    }
}
