package com.foodpurchasingsystem.foodpurchasingsystem.repository;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);
    Order findByUser(User user);
    Order findByUserAndOrderStatus(User user, String status);
}
