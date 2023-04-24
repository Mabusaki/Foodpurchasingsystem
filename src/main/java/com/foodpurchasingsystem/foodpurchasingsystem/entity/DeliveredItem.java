package com.foodpurchasingsystem.foodpurchasingsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class DeliveredItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private boolean isActive;
    private LocalDateTime deliverDate;
    public DeliveredItem(Product product, User user, Integer quantity, Order order, boolean isActive, LocalDateTime deliverDate) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.order = order;
        this.isActive = isActive;
        this.deliverDate = deliverDate;
    }

    public DeliveredItem() {
    }
}
