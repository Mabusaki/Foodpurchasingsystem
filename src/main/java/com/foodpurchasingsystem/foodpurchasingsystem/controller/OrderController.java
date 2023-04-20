package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Simply view all orders
    @GetMapping("/viewallorders")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<Order>> viewAllOrders() throws OrderException {
        return new ResponseEntity<List<Order>>(orderService.viewAllOrders(), HttpStatus.OK);
    }

    //View all orders placed by logged user
    @GetMapping("/volu")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<List<Order>> viewAllOrdersByUser() throws UserException, OrderException {
        return new ResponseEntity<List<Order>>(orderService.viewAllOrdersByLoggedUser(), HttpStatus.OK);
    }

    //Place order
    @PostMapping("/placeorder")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<Order> placeOrder() throws UserException {
        return new ResponseEntity<Order>(orderService.placeOrder(), HttpStatus.OK);
    }

    @PostMapping("/deliverorder")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<Order> deliverOrder() throws UserException {
        return new ResponseEntity<Order>(orderService.deliverOrder(), HttpStatus.OK);
    }

}
