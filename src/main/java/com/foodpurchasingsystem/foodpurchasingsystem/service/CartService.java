package com.foodpurchasingsystem.foodpurchasingsystem.service;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartItemException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;

import java.util.List;

public interface CartService {

    //TODO
    // Change cartId with userId if it doesn't satisfy

    List<CartItem> viewCart(Integer cartId) throws CartException;
    CartItem addItemToCart(Integer cartId, Integer productId)
            throws CartException, ProductException;
    CartItem removeItemFromCart(Integer cartId, Integer itemId) throws CartException, CartItemException;

    CartItem increaseItemQuantity(Integer cartId, Integer itemId) throws CartException, CartItemException;



}
