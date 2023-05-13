package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.DeliveredItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.DeliveredItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.OrderRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.service.OrderService;
import com.foodpurchasingsystem.foodpurchasingsystem.util.GetLoggedUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepo cartItemRepo;
    private final GetLoggedUser getLoggedUser;
    private final DeliveredItemRepo deliveredItemRepo;
    private final UserRepository userRepository;
    public OrderServiceImpl(OrderRepository orderRepository, CartItemRepo cartItemRepo, GetLoggedUser getLoggedUser, DeliveredItemRepo deliveredItemRepo, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepo = cartItemRepo;
        this.getLoggedUser = getLoggedUser;
        this.deliveredItemRepo = deliveredItemRepo;
        this.userRepository = userRepository;
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
          List<CartItem> cartItemList = cartItemRepo.findAllByUser(getLoggedUser.getCurrentUser());
          for(CartItem c : cartItemList) {
              c.setOrder(order);
          }
          cartItemRepo.saveAll(cartItemList);
        return order;
    }

    @Override
    public Order deliverOrder(Long userId) throws UserException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserException("User not found"));
        Order order = orderRepository.findByUserAndOrderStatus(user, "pending");
        order.setOrderStatus("delivered");
        List<CartItem> cartItems = cartItemRepo.findAllByUser(user);
        List<DeliveredItem> deliveredItems = new ArrayList<>();
        for(int i = 0; i<cartItems.size(); i++){
            DeliveredItem d = new DeliveredItem(cartItems.get(i).getProduct(), cartItems.get(i).getUser(),cartItems.get(i).getQuantity(), cartItems.get(i).getOrder(), false, LocalDateTime.now());
            deliveredItems.add(d);
        }
        deliveredItemRepo.saveAll(deliveredItems);
        cartItemRepo.deleteAll(cartItems);
        return order;
    }
}
