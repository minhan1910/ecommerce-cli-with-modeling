package com.springdatajpa.springboot.service;

import com.springdatajpa.springboot.dto.OrderRequest;
import com.springdatajpa.springboot.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
