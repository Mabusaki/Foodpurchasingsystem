package com.foodpurchasingsystem.foodpurchasingsystem.service;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;

import java.util.List;

public interface OrderService {
    List<Order> viewAllOrders() throws OrderException;
    List<Order> viewAllOrdersByLoggedUser() throws OrderException, UserException;
    Order placeOrder() throws UserException;
}
