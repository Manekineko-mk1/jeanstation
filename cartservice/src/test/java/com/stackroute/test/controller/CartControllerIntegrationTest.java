package com.stackroute.test.controller;

import com.stackroute.domain.Cart;
import com.stackroute.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class CartControllerIntegrationTest {

    private final CartService cartService;

    @Autowired
    public CartControllerIntegrationTest(CartService cartService) {
        this.cartService = cartService;
    }


    /**
     * save a new Cart
     */
    @PostMapping("/cart")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }


    /**
     * retrieve all Carts
     */
    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return new ResponseEntity<>(cartService.findAllCarts(), HttpStatus.OK);

    }

    /**
     * retrieve Cart by id
     */
    @GetMapping("cart/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable("cartId") String cartId) {
        return new ResponseEntity<>(cartService.findCartById(cartId), HttpStatus.FOUND);
    }

    /**
     * delete Cart by id
     */
    @DeleteMapping("cart/{cartId}")
    public ResponseEntity<Cart> getCartAfterDeleting(@PathVariable("CartId") String cartId) {
        return new ResponseEntity<>(cartService.deleteCartById(cartId), HttpStatus.OK);
    }

    /**
     * update Cart
     */
    @PutMapping("cart")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
    
}

