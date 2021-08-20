package com.stackroute.service;

import com.stackroute.domain.Cart;

import com.stackroute.exceptions.CartAlreadyExistException;
import com.stackroute.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    /**
     * Constructor based Dependency injection to inject CartRepository here
     */
    @Autowired
    CartRepository cartRepository;

    /**
     * Implementation of saveCart method
     */
    @Override
    public Cart saveCart(Cart cart) {
        boolean isCartExist;

        if(cart.getId() == null) {
            isCartExist = false;
        } else {
            isCartExist = cartRepository.findById(cart.getId()).isPresent();
        }

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isCartExist) {
            log.error("ERROR: Unable to add cart. Cart already existed in database | Cart ID: {} | Timestamp(EST): {}",
                    cart.getId(), timeStamp);

            throw new CartAlreadyExistException(cart.getId());
        } else {
            log.info("SUCCESS: Add a product to the \"carts\" collection | Cart ID: {} | Timestamp(EST): {}",
                    cart.getId(), timeStamp);

            return cartRepository.save(cart);
        }
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

