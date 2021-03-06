package com.kodilla.backend.order.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ADRDecorator extends AbstractOrderDecorator {
    public ADRDecorator(OrderInterface theOrder) {
        super(theOrder);
    }

    @Override
    public Double getCost() {
        double value = getDistance() * 0.0001 + 150;
        Double result = BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).doubleValue();
        return super.getCost() + result;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", ADR";
    }
}
