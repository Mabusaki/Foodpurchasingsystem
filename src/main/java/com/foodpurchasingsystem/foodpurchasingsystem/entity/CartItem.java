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

    @ManyToMany(mappedBy = "cartItemList")
    private List<Order> orders;

    public CartItem(Product product, User user, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public CartItem() {

    }
}
