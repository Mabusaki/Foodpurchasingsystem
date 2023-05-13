package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.*;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartItemException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.ProductRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CartItemService;
import com.foodpurchasingsystem.foodpurchasingsystem.util.GetLoggedUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserRepository userRepository;
    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;
    private final GetLoggedUser getLoggedUser;

    public CartItemServiceImpl(UserRepository userRepository, ProductRepo productRepo, CartItemRepo cartItemRepo, AuthenticationManager authenticationManager, GetLoggedUser getLoggedUser) {
        this.userRepository = userRepository;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
        this.getLoggedUser = getLoggedUser;
    }

    @Override
    public List<CartItem> viewCartItemsByUser() throws UserException {
        List<CartItem> cartItems = cartItemRepo.findAllByUser(getLoggedUser.getCurrentUser());
        return cartItems;
    }

    @Override
    public CartItem addProductToCart(Integer productId) throws UserException, ProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found in user's cart"));
        boolean exists = cartItemRepo.existsByUserAndProduct(getLoggedUser.getCurrentUser(), product);
        if(!exists){
            if(product.getInitialQuantity() >= 1){
                CartItem cartItem = new CartItem(product,getLoggedUser.getCurrentUser(),1, true);
                cartItemRepo.save(cartItem);
                product.setInitialQuantity(product.getInitialQuantity()-1);
                productRepo.save(product);
                return cartItem;
            }else throw new ProductException("Product sold out.");
        }else{
            throw new ProductException("Product already added, try to increase quantity.");
        }
    }

    @Override
    public CartItem removeProductFromCart(Integer productId) throws UserException, ProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found in user's cart"));
        CartItem cartItem = cartItemRepo.findCartItemByUserAndProduct(getLoggedUser.getCurrentUser(), product);
        cartItemRepo.delete(cartItem);
        return cartItem;
    }

    @Override
    public CartItem increaseQuantity(Integer productId) throws UserException, ProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found in user's cart"));
        CartItem cartItem = cartItemRepo.findCartItemByUserAndProduct(getLoggedUser.getCurrentUser(), product);
        if(product.getInitialQuantity() >= 1){
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItemRepo.save(cartItem);
            product.setInitialQuantity(product.getInitialQuantity()-1);
            productRepo.save(product);
            return cartItem;
        }else throw new ProductException("Product sold out");

    }

    @Override
    public CartItem decreaseQuantity(Integer productId) throws UserException, ProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found in user's cart"));
        CartItem cartItem = cartItemRepo.findCartItemByUserAndProduct(getLoggedUser.getCurrentUser(), product);
        cartItem.setQuantity(cartItem.getQuantity()-1);
        cartItemRepo.save(cartItem);
        return cartItem;
    }
}
