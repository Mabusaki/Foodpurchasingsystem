package com.foodpurchasingsystem.foodpurchasingsystem.service;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;

public interface CartService {
    Cart viewCart(Long userId, Integer cartId) throws CartException;
    Cart addItemToCart(Long userId, Integer productId)
            throws CartException, ProductException, UserException;

}
