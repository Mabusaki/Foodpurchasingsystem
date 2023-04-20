package com.foodpurchasingsystem.foodpurchasingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private LocalDateTime date;
    private String orderStatus;
    private Double totalPrice;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Order(LocalDateTime date, String orderStatus, Double totalPrice, User user) {
        this.date = date;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public Order() {

    }
}
