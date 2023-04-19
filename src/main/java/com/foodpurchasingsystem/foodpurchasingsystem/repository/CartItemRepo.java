package com.foodpurchasingsystem.foodpurchasingsystem.repository;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Product;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
import com.foodpurchasingsystem.foodpurchasingsystem.security.services.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemByUserAndProduct(User user, Product product);
    boolean existsByUserAndProduct(User user, Product product);
    List<CartItem> findAllByUser(User user);
}
