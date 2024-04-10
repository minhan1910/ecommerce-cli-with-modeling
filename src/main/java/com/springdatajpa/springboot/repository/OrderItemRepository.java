package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
