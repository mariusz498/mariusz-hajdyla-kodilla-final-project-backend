package com.kodilla.backend.order.decorator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDecoratorsTestSuite {

    @Test
    public void testBasicOrder() {
        //Given
        OrderInterface theOrder = new BasicOrder(100000);
        //When
        Double theCost = theOrder.getCost();
        String description = theOrder.getDescription();
        //Then
        Assert.assertEquals(160.0, theCost, 0.01);
        Assert.assertEquals("Land transport", description);
    }

    @Test
    public void testExpressDecorator() {
        //Given
        OrderInterface theOrder = new BasicOrder(100000);
        theOrder = new ExpressDecorator(theOrder);
        //When
        Double theCost = theOrder.getCost();
        String description = theOrder.getDescription();
        //Then
        Assert.assertEquals(180.00, theCost, 0.01);
        Assert.assertEquals("Land transport, Express", description);
    }

    @Test
    public void testADRDecorator() {
        //Given
        OrderInterface theOrder = new BasicOrder(100000);
        theOrder = new ADRDecorator(theOrder);
        //When
        Double theCost = theOrder.getCost();
        String description = theOrder.getDescription();
        //Then
        Assert.assertEquals(320.00, theCost, 0.01);
        Assert.assertEquals("Land transport, ADR", description);
    }

    @Test
    public void testFragileDecorator() {
        //Given
        OrderInterface theOrder = new BasicOrder(100000);
        theOrder = new FragileDecorator(theOrder);
        //When
        Double theCost = theOrder.getCost();
        String description = theOrder.getDescription();
        //Then
        Assert.assertEquals(170.00, theCost, 0.01);
        Assert.assertEquals("Land transport, FRAGILE LOAD", description);
    }
}
