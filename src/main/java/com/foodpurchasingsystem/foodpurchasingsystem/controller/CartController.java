package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartItemException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

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
    public ResponseEntity<CartItem> addProductToCart(@RequestParam("cartId") Integer cartId, @RequestParam("productId")
    Integer productId) throws CartException, ProductException {
        return new ResponseEntity<CartItem>(cartService.addItemToCart(cartId,productId), HttpStatus.OK);
    }

    @GetMapping("/viewcart/{cartId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<List<CartItem>> viewCartById(@PathVariable Integer cartId) throws CartException {
        return new ResponseEntity<List<CartItem>>(cartService.viewCart(cartId), HttpStatus.OK);
    }

    @DeleteMapping("/removeitem")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CartItem> removeItemFromCart(@RequestParam("cartId") Integer cartId, @RequestParam("itemId") Integer itemId) throws CartItemException, CartException {
        return new ResponseEntity<CartItem>(cartService.removeItemFromCart(cartId, itemId), HttpStatus.OK);
    }


    @PostMapping("/incritem")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CartItem> increaseItemQuantity(@RequestParam("cartId") Integer cartId, @RequestParam("itemId") Integer itemId) throws CartException, CartItemException {
        return new ResponseEntity<CartItem>(cartService.increaseItemQuantity(cartId, itemId), HttpStatus.OK);
    }



}
