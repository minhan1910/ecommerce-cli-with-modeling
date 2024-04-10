package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private void calculatePrice() {
        if (product != null) {
            this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
    }

    public static class OrderItemBuilder {
        public OrderItem build() {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(id);
            orderItem.setQuantity(quantity);
            orderItem.setProduct(product);
            orderItem.setImageUrl(imageUrl);
            orderItem.calculatePrice(); // Calculate the price before building
            return orderItem;
        }
    }
}
