package com.kodilla.backend.order.decorator;

import org.apache.commons.math3.util.Precision;

public class ExpressDecorator extends AbstractOrderDecorator {
    public ExpressDecorator(OrderInterface theOrder) {
            super(theOrder);
        }

        @Override
        public Double getCost() {
            double value = getDistance() * 0.0002;
            Double result = Precision.round(value, 2);
            return super.getCost() + result;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Express";
        }
}
