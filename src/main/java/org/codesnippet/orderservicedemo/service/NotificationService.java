package org.codesnippet.orderservicedemo.service;

import org.codesnippet.orderservicedemo.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(Order order) {

        throw new RuntimeException(
            "Email service unavailable"
        );
    }
}