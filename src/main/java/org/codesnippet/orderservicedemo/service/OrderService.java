package org.codesnippet.orderservicedemo.service;

import org.codesnippet.orderservicedemo.entity.Order;
import org.codesnippet.orderservicedemo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final NotificationService notificationService;

    public OrderService(OrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Transactional
    public void placeOrder(String productName) {

        validate(productName);

        saveOrder(productName);
    }

    private void validate(String productName) {

        if(productName.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public void saveOrder(String productName) {

        Order order = new Order(productName);

        repository.save(order);

        notificationService.sendNotification(order);
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrder(Long orderId) {
        return repository.findById(orderId);
    }

    @Transactional
    public boolean deleteOrder(Long orderId) {
        if (!repository.existsById(orderId)) {
            return false;
        }

        repository.deleteById(orderId);
        return true;
    }
}
