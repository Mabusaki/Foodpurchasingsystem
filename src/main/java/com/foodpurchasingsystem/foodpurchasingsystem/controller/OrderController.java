package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @RolesAllowed( "ROLE_ADMIN")
    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestParam("userId") Long userId) throws OrderException, UserException,
            CartException {
        return new ResponseEntity<Order>(orderService.addOrder(userId), HttpStatus.OK);
    }


    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    @GetMapping("/view/{orderId}")
    public ResponseEntity<Order> viewOrder(@PathVariable("orderId") Integer orderId) throws OrderException, UserException {
        return new ResponseEntity<Order>(orderService.viewOrder(orderId), HttpStatus.OK);
    }

}
