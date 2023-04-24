package com.foodpurchasingsystem.foodpurchasingsystem.repository;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.DeliveredItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveredItemRepo extends JpaRepository<DeliveredItem, Integer> {
}
