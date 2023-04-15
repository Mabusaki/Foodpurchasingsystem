package com.foodpurchasingsystem.foodpurchasingsystem.repository;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
}
