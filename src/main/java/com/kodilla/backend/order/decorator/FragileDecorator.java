package com.kodilla.backend.order.decorator;

import java.text.DecimalFormat;

public class FragileDecorator extends AbstractOrderDecorator {
    public FragileDecorator(OrderInterface theOrder) {
        super(theOrder);
    }

    @Override
    public Double getCost() {
        DecimalFormat dec = new DecimalFormat("#0.00");
        Double result = (new Double(getDistance() * 0.0001));
        return super.getCost() + result;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", FRAGILE LOAD";
    }
}
