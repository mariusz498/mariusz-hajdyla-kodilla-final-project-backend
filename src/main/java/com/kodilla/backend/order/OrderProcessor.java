package com.kodilla.backend.order;

import com.kodilla.backend.currencyApi.client.CurrencyApiClient;
import com.kodilla.backend.domain.Order;
import com.kodilla.backend.domain.OrderDto;
import com.kodilla.backend.domain.OrderRequestDto;
import com.kodilla.backend.hereApi.client.HereApiClient;
import com.kodilla.backend.mapper.CompanyMapper;
import com.kodilla.backend.mapper.LocationMapper;
import com.kodilla.backend.mapper.OrderMapper;
import com.kodilla.backend.order.decorator.ADRDecorator;
import com.kodilla.backend.order.decorator.BasicOrder;
import com.kodilla.backend.order.decorator.ExpressDecorator;
import com.kodilla.backend.order.decorator.FragileDecorator;
import com.kodilla.backend.order.decorator.OrderInterface;
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
        if(!request.getCurrency().equals("EUR")) {
            Double ratio = currencyApiClient.convert(request.getCurrency());
            value = theOrder.getCost() * 1.05 * ratio;
        }
        else {
            value = theOrder.getCost();
        }

        Order createdOrder = new Order();
        createdOrder.setDescription(theOrder.getDescription());
        createdOrder.setCompany(companyMapper.mapToCompany(request.getCompany()));
        createdOrder.setOrigin(locationMapper.mapToLocation(request.getOrigin()));
        createdOrder.setDestination(locationMapper.mapToLocation(request.getDestination()));
        createdOrder.setValue(value);
        createdOrder.setCurrency(request.getCurrency());
        createdOrder.setStatus("Available");

        return createdOrder;
    }

}
