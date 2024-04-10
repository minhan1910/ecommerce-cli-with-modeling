package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void saveAddress() {
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
//        billingAddress.setOrder(order);

        addressRepository.save(billingAddress);
    }

    @Test
    void updateAddress() {
        Address address = addressRepository.findById(3L).get();
        address.setCity("Long An");
//        address.getOrder().setTotalQuantity(10);

        addressRepository.save(address);
    }

    @Test
    void get() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order.getBillingAddress());
    }

}
