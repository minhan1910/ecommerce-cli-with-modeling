package com.springdatajpa.springboot.dto;

import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
