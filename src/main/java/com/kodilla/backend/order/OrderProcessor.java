package com.kodilla.backend.order;

import com.kodilla.backend.currencyApi.client.CurrencyApiClient;
import com.kodilla.backend.domain.*;
import com.kodilla.backend.hereApi.client.HereApiClient;
import com.kodilla.backend.mapper.CompanyMapper;
import com.kodilla.backend.mapper.LocationMapper;
import com.kodilla.backend.mapper.OrderMapper;
import com.kodilla.backend.order.decorator.ADRDecorator;
import com.kodilla.backend.order.decorator.BasicOrder;
import com.kodilla.backend.order.decorator.ExpressDecorator;
import com.kodilla.backend.order.decorator.FragileDecorator;
import com.kodilla.backend.order.decorator.OrderInterface;
import com.kodilla.backend.service.DbService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    @Autowired
    private HereApiClient hereApiClient;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CurrencyApiClient currencyApiClient;

    @Autowired
    private DbService dbService;

    public Order createOrder(OrderRequestDto request) {
        Integer distance = hereApiClient.searchRouteLength(request);
        OrderInterface theOrder = new BasicOrder(distance);
        if(request.getOptions().get("Express")) {
            theOrder = new ExpressDecorator(theOrder);
        }
        if(request.getOptions().get("ADR")) {
            theOrder = new ADRDecorator(theOrder);
        }
        if(request.getOptions().get("Fragile")) {
            theOrder = new FragileDecorator(theOrder);
        }
        Double value;
        Double result;
        if(!request.getCurrency().equals("EUR")) {
            Double ratio = currencyApiClient.convert(request.getCurrency());
            value = theOrder.getCost() * 1.05 * ratio;
            result = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        else {
            value = theOrder.getCost();
            result = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }

        Order createdOrder = new Order();
        createdOrder.setDescription(theOrder.getDescription());
        createdOrder.setCompany(dbService.getCompanyByLogin(request.getCompany()).orElse(new Company()));
        createdOrder.setOrigin(locationMapper.mapToLocation(request.getOrigin()));
        createdOrder.setDestination(locationMapper.mapToLocation(request.getDestination()));
        createdOrder.setDriver(new Driver());
        createdOrder.setValue(result);
        createdOrder.setCurrency(request.getCurrency());
        createdOrder.setStatus("Available");

        return createdOrder;
    }

}
