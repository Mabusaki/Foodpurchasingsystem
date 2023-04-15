package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.OrderRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.service.OrderService;
import org.springframework.stereotype.Service;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepo orderRepo, UserRepository userRepository) {
        this.orderRepo = orderRepo;
        this.userRepository = userRepository;
    }

    @Override
    public Order addOrder(Long userId) throws OrderException, UserException, CartException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserException("User not found"));
        return null;
    }

    @Override
    public Order viewOrder(Integer orderId) throws OrderException {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new OrderException("Order not found"));
        return null;
    }
}
