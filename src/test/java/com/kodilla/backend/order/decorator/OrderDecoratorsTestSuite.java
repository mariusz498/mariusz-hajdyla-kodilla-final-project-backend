package com.kodilla.backend.order.decorator;

import org.junit.Assert;
import org.junit.Test;

public class OrderDecoratorsTestSuite {

    @Test
    public void testBasicOrder() {
        //Given
        OrderInterface theOrder = new BasicOrder(100.0);
        //When
        Double theCost = theOrder.getCost();
        //Then
        Assert.assertEquals(160.0, theCost, 0.01);
    }

    @Test
    public void testExpressDecorator() {
        //Given
        OrderInterface theOrder = new BasicOrder(100.0);
        theOrder = new ExpressDecorator(theOrder);
        //When
        Double theCost = theOrder.getCost();
        //Then
        Assert.assertEquals(180.00, theCost, 0.01);
    }
}
