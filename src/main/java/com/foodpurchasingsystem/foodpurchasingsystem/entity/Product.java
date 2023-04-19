package com.foodpurchasingsystem.foodpurchasingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private double price;
    private String filePath;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;


//    @JsonIgnore
//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinTable(name = "item_product",
//            joinColumns =
//                    {@JoinColumn(name = "product_id", referencedColumnName = "productId")} ,
//            inverseJoinColumns =
//                    {@JoinColumn(name = "item_id", referencedColumnName = "itemId")})
//    private CartItem cartItem;
}
