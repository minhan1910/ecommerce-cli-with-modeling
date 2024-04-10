package com.springdatajpa.springboot.service;

import com.springdatajpa.springboot.dto.OrderRequest;
import com.springdatajpa.springboot.dto.OrderResponse;
import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.Payment;
import com.springdatajpa.springboot.exception.PaymentException;
import com.springdatajpa.springboot.repository.OrderRepository;
import com.springdatajpa.springboot.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        order.setStatus("IN PROGRESS");
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type does not support.");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        return OrderResponse.builder()
                .orderTrackingNumber(order.getOrderTrackingNumber())
                .status(order.getStatus())
                .message("SUCCESS")
                .build();
    }
}
