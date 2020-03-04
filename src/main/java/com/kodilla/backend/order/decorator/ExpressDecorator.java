package com.kodilla.backend.order.decorator;

public class ExpressDecorator extends AbstractOrderDecorator {
    public ExpressDecorator(OrderInterface theOrder) {
            super(theOrder);
        }

        @Override
        public Double getCost() {
            return super.getCost() + (new Double(getDistance() * 0.2));
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", express";
        }
}
