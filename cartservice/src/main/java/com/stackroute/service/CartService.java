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
    List<Cart> findAllCarts();

    /**
     * AbstractMethod to get cart by id
     */
    Cart findCartById(String id);

    /**
     * AbstractMethod to delete cart by id
     */
    Cart deleteCartById(String id);

    /**
     * AbstractMethod to update a cart
     */
    Cart updateCart(Cart cart);
}
