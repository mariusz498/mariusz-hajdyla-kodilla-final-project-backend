package com.kodilla.backend.order.decorator;

public class FragileDecorator extends AbstractOrderDecorator {
    public FragileDecorator(OrderInterface theOrder) {
        super(theOrder);
    }

    @Override
    public Double getCost() {
        return super.getCost() + (new Double(getDistance() * 0.1));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", FRAGILE LOAD";
    }
}
