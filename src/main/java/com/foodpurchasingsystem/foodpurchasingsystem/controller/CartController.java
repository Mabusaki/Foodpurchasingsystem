package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<Cart> addProductToCart(@RequestParam("userId") Long userId, @RequestParam("productId")
    Integer productId) throws CartException, UserException, ProductException {
        return new ResponseEntity<Cart>(cartService.addItemToCart(userId,productId), HttpStatus.OK);
    }

//    @GetMapping("/viewcart/{cartId}")
//    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
//    public ResponseEntity<Cart> viewCartById(@PathVariable Integer cartId) throws CartException {
//        return new ResponseEntity<Cart>(cartService.viewCart(cartId), HttpStatus.OK);
//    }
}
