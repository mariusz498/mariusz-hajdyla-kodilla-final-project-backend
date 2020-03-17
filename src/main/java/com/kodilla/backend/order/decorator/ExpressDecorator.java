package com.kodilla.backend.order.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ExpressDecorator extends AbstractOrderDecorator {
    public ExpressDecorator(OrderInterface theOrder) {
            super(theOrder);
        }

        @Override
        public Double getCost() {
            Double value = getDistance() * 0.0002;
            Double result = BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).doubleValue();
            return super.getCost() + result;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", express";
        }
}
