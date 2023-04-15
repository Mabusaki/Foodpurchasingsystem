package com.foodpurchasingsystem.foodpurchasingsystem.repository;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findByProduct(Product product);
}
