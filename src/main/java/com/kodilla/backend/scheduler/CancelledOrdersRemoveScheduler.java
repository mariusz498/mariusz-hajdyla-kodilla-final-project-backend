package com.kodilla.backend.scheduler;

import com.kodilla.backend.domain.Order;
import com.kodilla.backend.service.DbService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CancelledOrdersRemoveScheduler {

    @Autowired
    private DbService dbService;

    @Scheduled(cron = "0 0 10 * * *")
    public void removeCancelledOrders() {
        List<Order> orders = dbService.getAllOrders();
        for(Order order : orders) {
            if(order.getStatus().equals("Cancelled")){
                dbService.deleteOrder(order.getId());
            }
        }
    }
}
