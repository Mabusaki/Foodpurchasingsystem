package com.foodpurchasingsystem.foodpurchasingsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    @OneToOne
    private Product product;

    private Integer quantity;

    public CartItem( Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem() {

    }
}
