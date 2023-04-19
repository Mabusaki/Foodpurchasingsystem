package com.foodpurchasingsystem.foodpurchasingsystem.service;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;


import java.util.List;

public interface CartItemService {
    List<CartItem> viewCartItemsByUser() throws UserException;
    CartItem addProductToCart(Integer productId) throws UserException, ProductException;
    CartItem removeProductFromCart(Integer productId) throws UserException, ProductException;
    CartItem increaseQuantity(Integer productId) throws UserException, ProductException;
    CartItem decreaseQuantity(Integer productId) throws UserException, ProductException;

}
