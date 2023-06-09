package com.foodpurchasingsystem.foodpurchasingsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

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
    public CartItem(Product product, User user, Integer quantity, boolean isActive) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.isActive = isActive;
    }

    public CartItem() {

    }
}
