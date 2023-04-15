package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.Cart;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.CartItem;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Product;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CartItemException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CartRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.ProductRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CartItem> viewCart(Integer cartId) throws CartException {
        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new CartException("Cart not found"));
        return cart.getCartItems();
    }

    @Override
    public CartItem addItemToCart(Integer cartId, Integer productId) throws CartException, ProductException {
        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new CartException("Cart not found"));
        Product product = productRepo.findById(productId).orElseThrow(()->new ProductException("Product not found"));
        CartItem cartItem = new CartItem(product,1);
        cart.getCartItems().add(cartItem);
        cartItemRepo.save(cartItem);
        cartRepo.save(cart);
        return cartItem;
    }

    @Override
    public CartItem removeItemFromCart(Integer cartId, Integer itemId) throws CartException, CartItemException {
        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new CartException("Cart not found"));
        CartItem cartItem = cartItemRepo.findById(itemId).orElseThrow(()-> new CartItemException("Item not found"));
        cart.getCartItems().remove(cartItem);
        cartItemRepo.delete(cartItem);
        cartRepo.save(cart);
        return cartItem;
    }

    @Override
    public CartItem increaseItemQuantity(Integer cartId, Integer productId) throws CartException, ProductException {
        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new CartException("Cart not found"));
        Product product = productRepo.findById(productId).orElseThrow(()->new ProductException("Product not found"));
        CartItem cartItem = cartItemRepo.findByProduct(product);
        int itemQuantity = cartItem.getQuantity();
        cartItem.setQuantity(itemQuantity+1);
        cartItemRepo.save(cartItem);
        cartRepo.save(cart);
        return cartItem;
    }



}
