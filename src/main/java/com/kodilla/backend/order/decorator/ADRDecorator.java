package com.kodilla.backend.order.decorator;

import java.text.DecimalFormat;

public class ADRDecorator extends AbstractOrderDecorator {
    public ADRDecorator(OrderInterface theOrder) {
        super(theOrder);
    }

    @Override
    public Double getCost() {
        DecimalFormat dec = new DecimalFormat("#0.00");
        Double result = (new Double(dec.format(new Double(getDistance() * 0.0001 + 150))));
        return super.getCost() + (result);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", ADR";
    }
}
