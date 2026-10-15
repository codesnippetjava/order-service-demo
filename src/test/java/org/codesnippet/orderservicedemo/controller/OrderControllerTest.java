package org.codesnippet.orderservicedemo.controller;

import org.codesnippet.orderservicedemo.entity.Order;
import org.codesnippet.orderservicedemo.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnOrderWhenFound() throws Exception {
        Order saved = repository.save(new Order("MacBook"));

        mockMvc.perform(get("/orders/{orderId}", saved.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + saved.getId() + ",\"productName\":\"MacBook\"}"));
    }

    @Test
    void shouldReturnNotFoundWhenOrderAbsent() throws Exception {
        mockMvc.perform(get("/orders/{orderId}", Long.MAX_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteOrderWhenFound() throws Exception {
        Order saved = repository.save(new Order("MacBook"));

        mockMvc.perform(delete("/orders/{orderId}", saved.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingMissingOrder() throws Exception {
        mockMvc.perform(delete("/orders/{orderId}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}
