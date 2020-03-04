package com.kodilla.backend.order.decorator;

public class AbstractOrderDecorator implements OrderInterface {

    private final OrderInterface orderInterface;

    protected AbstractOrderDecorator(OrderInterface orderInterface) {
        this.orderInterface = orderInterface;
    }

    @Override
    public Double getCost() {
        return orderInterface.getCost();
    }

    @Override
    public String getDescription() {
        return orderInterface.getDescription();
    }
}
