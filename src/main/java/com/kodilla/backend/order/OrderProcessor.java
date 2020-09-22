package com.kodilla.backend.order;

import com.kodilla.backend.currencyApi.client.CurrencyApiClient;
import com.kodilla.backend.domain.Company;
import com.kodilla.backend.domain.Driver;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.hereApi.client.HereApiClient;
import com.kodilla.backend.mapper.CompanyMapper;
import com.kodilla.backend.mapper.LocationMapper;
import com.kodilla.backend.order.decorator.*;
import com.kodilla.backend.service.DbService;
import org.apache.commons.math3.util.Precision;
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
        double result;
        if(!request.getCurrency().equals("EUR")) {
            Double ratio = currencyApiClient.convert(request.getCurrency());
            value = theOrder.getCost() * 1.05 * ratio;
        }
        else {
            value = theOrder.getCost();
        }
        result = Precision.round(value, 2);

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
