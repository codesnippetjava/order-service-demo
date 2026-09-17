package org.codesnippet.orderservicedemo.service;

import org.codesnippet.orderservicedemo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository repository;

    @Test
    void shouldRollbackWhenNotificationFails() {

        assertThrows(
            RuntimeException.class,
            () -> orderService.placeOrder("MacBook")
        );

        assertEquals(
            0,
            repository.count()
        );
    }
}