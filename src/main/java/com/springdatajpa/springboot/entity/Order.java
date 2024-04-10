package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

//    @OneToOne(cascade = CascadeType.ALL,
//            mappedBy = "order")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "order"
    )
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public BigDecimal getTotalPrice() {
        var totalAmount = BigDecimal.ZERO;

        for (var orderItem : this.orderItems) {
            totalAmount = totalAmount.add(orderItem.getPrice());
        }

        return totalAmount;
    }

    public int getTotalQuantity() {
        return this.orderItems
                .stream()
                .map(orderItem -> orderItem.getQuantity())
                .reduce(0, Integer::sum);
    }

    public static class OrderBuilder {
        private Set<OrderItem> orderItems = new HashSet<>();
        private Address billingAddress;

        public Order build() {
            var order = new Order();

            order.setId(id);
            order.setOrderTrackingNumber(orderTrackingNumber);
            order.setBillingAddress(this.billingAddress);
            order.setStatus(status);
            order.setOrderItems(this.orderItems);
            order.setTotalQuantity(order.getTotalQuantity());
            order.setTotalPrice(order.getTotalPrice());

            return order;
        }

        public OrderBuilder addOrderItem(OrderItem orderItem) {
            orderItems.add(orderItem);
            return this;
        }

        public OrderBuilder addAddress(Address address) {
            this.billingAddress = address;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "billingAddress=" + billingAddress +
                ", lastUpdated=" + lastUpdated +
                ", dateCreated=" + dateCreated +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalQuantity=" + totalQuantity +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", id=" + id +
                '}';
    }
}
