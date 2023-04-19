package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {
    @Autowired
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/viewallbyuser")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<List<CartItem>> viewAllCartItemsByUserId() throws UserException {
        return new ResponseEntity<List<CartItem>>(cartItemService.viewCartItemsByUser(), HttpStatus.OK);
    }


    @PostMapping("/addproduct/{productId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CartItem> addProductToCart(@PathVariable Integer productId) throws UserException, ProductException {
        return new ResponseEntity<CartItem>(cartItemService.addProductToCart(productId), HttpStatus.OK);
    }

    @PostMapping("/removeproduct/{productId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CartItem> removeProductFromCart(@PathVariable Integer productId) throws UserException, ProductException {
        return new ResponseEntity<CartItem>(cartItemService.removeProductFromCart(productId), HttpStatus.OK);
    }

    @PutMapping("/incquantity/{productId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CartItem> increaseQuantity(@PathVariable Integer productId) throws UserException, ProductException {
        return new ResponseEntity<CartItem>(cartItemService.increaseQuantity(productId), HttpStatus.OK);
    }
    @PutMapping("/decquantity/{productId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CartItem> decreaseQuantity(@PathVariable Integer productId) throws UserException, ProductException {
        return new ResponseEntity<CartItem>(cartItemService.decreaseQuantity(productId), HttpStatus.OK);
    }

}
