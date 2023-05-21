package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Order;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.OrderException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.OrderService;
import com.foodpurchasingsystem.foodpurchasingsystem.util.ExcelUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final ExcelUtil excelUtil;
    public OrderController(OrderService orderService, ExcelUtil excelUtil) {
        this.orderService = orderService;
        this.excelUtil = excelUtil;
    }

    //Simply view all orders
    @GetMapping("/viewallorders")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<List<Order>> viewAllOrders() throws OrderException {
        return new ResponseEntity<List<Order>>(orderService.viewAllOrders(), HttpStatus.OK);
    }

    //View all orders placed by logged user
    @GetMapping("/viewbylogged")
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

    @PostMapping("/deliverorder/{userId}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<Order> deliverOrder(@PathVariable Long userId) throws UserException {
        return new ResponseEntity<Order>(orderService.deliverOrder(userId), HttpStatus.OK);
    }

    @GetMapping("/exporttoexcel")
    @RolesAllowed("ROLE_ADMIN")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("appication/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=deliveredorder.xls";

        response.setHeader(headerKey, headerValue);

        excelUtil.generateExcelFile(response);
    }



}
