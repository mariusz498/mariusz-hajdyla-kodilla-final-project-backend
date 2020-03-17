package com.kodilla.backend.currencyApi.client;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyApiClientTestSuite {

    @Autowired
    private CurrencyApiClient currencyApiClient;

    @Test
    public void convertTest() {
        // When
        Double value = currencyApiClient.convert("PLN");
        //Then
        Assert.assertTrue(value > 0.0);
    }

    @Test
    public void illegalQueryConvertTest() {
        // When
        Double value = currencyApiClient.convert("");
        //Then
        Assert.assertEquals(-1.0, value, 0.001);
    }
}
