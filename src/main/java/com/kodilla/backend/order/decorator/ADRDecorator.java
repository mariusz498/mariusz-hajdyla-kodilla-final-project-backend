package com.kodilla.backend.order.decorator;

public class ADRDecorator extends AbstractOrderDecorator {
    public ADRDecorator(OrderInterface theOrder) {
        super(theOrder);
    }

    @Override
    public Double getCost() {
        return super.getCost() + (new Double(getDistance() * 0.1 + 150));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", ADR";
    }
}
