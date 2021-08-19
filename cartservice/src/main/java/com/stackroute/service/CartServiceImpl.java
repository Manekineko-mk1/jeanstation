package com.stackroute.service;

import com.stackroute.domain.Cart;

import com.stackroute.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    /**
     * Constructor based Dependency injection to inject CartRepository here
     */
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Implementation of saveCart method
     */
    @Override
    public Cart saveCart(Cart cart) {
        boolean isCartExist = cartRepository.findById(cart.getId()).isPresent();
        return cartRepository.save(cart);
    }

    /**
     * Implementation of getAllCarts method
     */
    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    /**
     * Implementation of getCartById method
     */
    @Override
    public Cart getCartById(String id) {
        Cart cart;
        cart = cartRepository.findById(id).get();
        return cart;
    }

    /**
     * Implementation of deleteCartById method
     */
    @Override
    public Cart deleteCart(String id) {
        Cart cart = null;
        Optional<Cart> optional = cartRepository.findById(id);
        if (optional.isPresent()) {
            cart = cartRepository.findById(id).get();
            cartRepository.deleteById(id);
        }
        return cart;
    }

    /**
     * Implementation of updateCart method
     */
    @Override
    public Cart updateCart(Cart cart) {
        Cart updatedCart = null;
        Optional<Cart> optional = cartRepository.findById(cart.getId());
        if (optional.isPresent()) {
            Cart getCart = cartRepository.findById(cart.getId()).get();
            getCart.setPriceTotal(cart.getPriceTotal());
            getCart.setCartItems(cart.getCartItems());
            updatedCart = saveCart(getCart);
            System.out.println(updatedCart);
        }
        return updatedCart;
    }
}

