package com.stackroute.service;

import com.stackroute.domain.Cart;

import com.stackroute.exceptions.CartAlreadyExistException;
import com.stackroute.exceptions.CartNotFoundException;
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
            Cart addedCart = cartRepository.save(cart);

            log.info("SUCCESS: Add a cart to the \"carts\" collection | Cart ID: {} | Timestamp(EST): {}",
                    addedCart.getId(), timeStamp);

            return addedCart;
        }
    }

    /**
     * Implementation of getAllCarts method
     */
    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    /**
     * Implementation of getCartById method
     */
    @Override
    public Cart findCartById(String id) {
        boolean isCartExist = cartRepository.findById(id).isPresent();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isCartExist) {
            log.info("SUCCESS: Cart found by ID | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            return cartRepository.findById(id).get();
        } else {
            log.error("ERROR: Unable to find cart. Cart ID not found | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new CartNotFoundException(id);
        }
    }

    /**
     * Implementation of deleteCartById method
     */
    @Override
    public Cart deleteCartById(String id) {
        Cart cart;
        Optional<Cart> optional = cartRepository.findById(id);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            cart = cartRepository.findById(id).get();
            cartRepository.deleteById(id);

            log.info("SUCCESS: Deleted cart by ID | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            return cart;
        } else {
            log.error("ERROR: Unable to delete cart. Cart ID not found | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new CartNotFoundException(id);
        }
    }

    /**
     * Implementation of updateCart method
     */
    @Override
    public Cart updateCart(Cart cart) {
        Cart updatedCart;
        Optional<Cart> optional = cartRepository.findById(cart.getId());

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            // Locate the existing cart with same cart ID
            Cart getCart = cartRepository.findById(cart.getId()).get();

            // Update the existing product with new info
            getCart.setPriceTotal(cart.getPriceTotal());
            getCart.setCartItems(cart.getCartItems());

            // Update the existing product to the DB
            cartRepository.save(getCart);

            // Retrieve the updated product for return
            updatedCart = cartRepository.findById(cart.getId()).get();

            log.info("SUCCESS: Updated cart to the \"carts\" collection | Cart ID: {} | Timestamp(EST): {}",
                    cart.getId(), timeStamp);

            return updatedCart;
        } else {
            log.error("ERROR: Unable to empty cart. Cart ID not found | Cart ID: {} | Timestamp(EST): {}", cart.getId(), timeStamp);

            throw new CartNotFoundException(cart.getId());
        }
    }
}

