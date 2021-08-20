package com.stackroute.service;

import com.stackroute.domain.Cart;
import java.util.List;

public interface CartService {
    /**
     * AbstractMethod to save a cart
     */
    Cart saveCart(Cart cart);

    /**
     * AbstractMethod to get all carts
     */
    List<Cart> getAllCarts();

    /**
     * AbstractMethod to get cart by id
     */
    Cart getCartById(String id);

    /**
     * AbstractMethod to delete cart by id
     */
    Cart deleteCart(String id);

    /**
     * AbstractMethod to update a cart
     */
    Cart updateCart(Cart cart);
}
