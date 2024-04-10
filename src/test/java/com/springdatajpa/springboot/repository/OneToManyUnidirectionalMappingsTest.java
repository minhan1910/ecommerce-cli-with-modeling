package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OneToManyUnidirectionalMappingsTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveOrder() {
        OrderItem orderItem1 = OrderItem.builder()
                .quantity(2)
                .product(productRepository.findById(10L).get())
                .imageUrl("image1.png")
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .quantity(3)
                .product(productRepository.findById(11L).get())
                .imageUrl("image2.png")
                .build();

        Address billingAddress = Address.builder()
                .street("Au duong lan")
                .city("HCM")
                .country("VN")
                .state("Viet Nam")
                .zipCode("71000")
                .build();

        Order order = Order.builder()
                .orderTrackingNumber("100ABCDD")
                .status("IN PROGRESS")
                .addOrderItem(orderItem1)
                .addOrderItem(orderItem2)
                .build();

        order.setBillingAddress(billingAddress);

        orderRepository.save(order);
    }

    @Test
    void get() {
        Order order = orderRepository.findById(3L).get();
        System.out.println(order.getBillingAddress());
    }

    @Test
    void delete() {
        orderRepository.deleteById(5L);
        orderRepository.deleteById(6L);
    }

}
