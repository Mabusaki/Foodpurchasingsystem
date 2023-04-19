package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.OrderRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.service.OrderService;
import com.foodpurchasingsystem.foodpurchasingsystem.util.GetLoggedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepo cartItemRepo;
    private final GetLoggedUser getLoggedUser;
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CartItemRepo cartItemRepo, GetLoggedUser getLoggedUser) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemRepo = cartItemRepo;
        this.getLoggedUser = getLoggedUser;
    }

    @Override
    public List<Order> viewAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }


    @Override
    public List<Order> viewAllOrdersByLoggedUser() throws UserException, OrderException {
        List<Order> userOrders = orderRepository.findAllByUser(getLoggedUser.getCurrentUser());
        if(userOrders.size()>0) return userOrders;
        else throw new OrderException("Order not found by user");
    }

    @Override
    public Order placeOrder() throws UserException {
          List<CartItem> cartItems = cartItemRepo.findAllByUser(getLoggedUser.getCurrentUser());
          Double totalPrice = 0.0;
          for(int i = 0; i<cartItems.size(); i++){
              double pricePerProduct=cartItems.get(i).getQuantity()*cartItems.get(i).getProduct().getPrice();
              totalPrice += pricePerProduct;
          }
          Order order = new Order(LocalDateTime.now(), "pending", totalPrice , getLoggedUser.getCurrentUser());
          orderRepository.save(order);
        return order;

    }
}
