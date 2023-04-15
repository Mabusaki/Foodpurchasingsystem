package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.ProductRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo, CartItemRepo cartItemRepo, UserRepository userRepository, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.userRepository = userRepository;
        this.productRepo = productRepo;
    }

    private final CartItemRepo cartItemRepo;
    private final UserRepository userRepository;
    private final ProductRepo productRepo;
    @Override
    public Cart viewCart(Long userId, Integer cartId) throws CartException {
        return null;
    }

    @Override
    public Cart addItemToCart(Long userId, Integer productId) throws CartException, ProductException, UserException {
        return null;
    }
}
