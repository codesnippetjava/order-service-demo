package org.codesnippet.orderservicedemo.service;

import org.codesnippet.orderservicedemo.entity.Order;
import org.codesnippet.orderservicedemo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldRejectBlankProductNameBeforeSaving() {
        assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder("   "));

        verifyNoInteractions(repository, notificationService);
    }

    @Test
    void shouldSaveOrderAndSendNotification() {
        orderService.placeOrder("MacBook");

        verify(repository).save(argThat(order -> "MacBook".equals(order.getProductName())));
        verify(notificationService).sendNotification(
                argThat(order -> "MacBook".equals(order.getProductName())));
    }

    @Test
    void shouldReturnOrderWhenFound() {
        Order order = new Order("MacBook");
        when(repository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrder(1L);

        assertTrue(result.isPresent());
        assertSame(order, result.orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenOrderIsMissing() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertTrue(orderService.getOrder(1L).isEmpty());
    }

    @Test
    void shouldDeleteExistingOrder() {
        when(repository.existsById(1L)).thenReturn(true);

        assertTrue(orderService.deleteOrder(1L));

        verify(repository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteMissingOrder() {
        when(repository.existsById(1L)).thenReturn(false);

        assertFalse(orderService.deleteOrder(1L));

        verify(repository).existsById(1L);
        verify(repository, never()).deleteById(1L);
    }
}
