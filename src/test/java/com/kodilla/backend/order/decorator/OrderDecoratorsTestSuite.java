package com.kodilla.backend.order.decorator;

import org.junit.Assert;
import org.junit.Test;

public class OrderDecoratorsTestSuite {

    @Test
    public void testBasicOrder() {
        //Given
        OrderInterface theOrder = new BasicOrder();
        //When
        Double theCost = theOrder.getCost();
        //Then
        Assert.assertEquals(100.00, theCost, 0.01);
    }

    @Test
    public void testExpressDecorator() {
        //Given
        OrderInterface theOrder = new BasicOrder();
        theOrder = new ExpressDecorator(theOrder, 120.00);
        //When
        Double theCost = theOrder.getCost();
        //Then
        Assert.assertEquals(244.00, theCost, 0.01);
    }
}
