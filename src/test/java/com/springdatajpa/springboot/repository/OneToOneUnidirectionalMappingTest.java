package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrder() {
        Order order = Order.builder()
                .orderTrackingNumber("100ABC")
                .totalQuantity(5)
                .status("IN PROGRESS")
                .totalPrice(new BigDecimal(1000))
                .build();

        Address billingAddress = Address.builder()
                .street("Nguyen Trai")
                .city("HCM")
                .country("VN")
                .state("Viet Nam")
                .zipCode("70000")
                .build();

        order.setBillingAddress(billingAddress);

        Order orderSaved = orderRepository.save(order);

        System.out.println(orderSaved);
    }

    @Test
    void updateOrder() {
        Long id = 1L;
        Order order = orderRepository.findById(id).get();

        order.setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("710000");

        Order orderSaved = orderRepository.save(order);

        System.out.println(orderSaved);
    }

    @Test
    void deleteOrder() {
        Long id = 1L;
        orderRepository.deleteById(id);
    }


}
