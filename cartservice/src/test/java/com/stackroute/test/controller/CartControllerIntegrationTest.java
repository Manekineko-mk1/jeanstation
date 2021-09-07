package com.stackroute.test.controller;

import com.stackroute.domain.Cart;
import com.stackroute.domain.Product;
import com.stackroute.exceptions.CartAlreadyExistException;
import com.stackroute.exceptions.CartNotFoundException;
import com.stackroute.repository.CartRepository;
import com.stackroute.service.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartControllerIntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    private Cart cart;
    private List<Product> cartList;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.setId("1l");
        cart.setPriceTotalBeforeTax(100);
        cart.setPriceTotalAfterTax(114);
        cart.setCartItems(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        cartRepository.deleteAll();
        cart = null;
    }

    @Test
    void givenProductToSaveThenShouldReturnTheSavedProduct() throws CartAlreadyExistException {
        Cart savedCart = cartService.saveCart(cart);
        assertNotNull(savedCart);
        assertEquals(cart.getId(), savedCart.getId());
    }

    @Test
    void givenProductToSaveThenThrowException() throws CartAlreadyExistException {
        assertNotNull(cartService.saveCart(cart));
        assertThrows(CartAlreadyExistException.class, () -> cartService.saveCart(cart));
    }

    @Test
    void givenProductToDeleteThenShouldReturnTheDeletedProduct() throws CartNotFoundException {
        assertNotNull(cartService.saveCart(cart));
        Cart deletedCart = cartService.deleteCartById(cart.getId());
        assertNotNull(deletedCart);
    }

    @Test
    void givenProductToDeleteThenThrowException() throws CartNotFoundException {
        cart.setId("NonExistCartId");
        assertThrows(CartNotFoundException.class, () -> cartService.deleteCartById(cart.getId()));
    }

    @Test
    void givenCallToGetAllProductsThenListShouldNotBeNull() throws Exception {
        List<Cart> retrievedProducts = cartService.findAllCarts();
        assertNotNull(retrievedProducts);
    }

    @Test
    void givenProductToUpdateThenShouldReturnUpdatedProduct() throws CartNotFoundException {
        Cart savedCart = cartService.saveCart(cart);
        savedCart.setPriceTotalBeforeTax(10);
        Cart updatedCart = cartService.updateCart(savedCart);
        assertNotNull(updatedCart);
        assertEquals(10, updatedCart.getPriceTotalBeforeTax());
    }

    @Test
    void givenProductToUpdateThenThrowException() throws CartNotFoundException {
        cart.setId("NonExistCartId");
        assertThrows(CartNotFoundException.class, () -> cartService.updateCart(cart));
    }

    @Test
    void givenProductIdThenShouldReturnRespectiveProduct() throws CartNotFoundException {
        assertNotNull(cartService.saveCart(cart));
        Cart retrievedCart = cartService.findCartById(cart.getId());
        assertNotNull(retrievedCart);
    }

    @Test
    void givenProductIdThenShouldThrowException() throws CartNotFoundException {
        cart.setId("NonExistCartId");
        assertThrows(CartNotFoundException.class, () -> cartService.findCartById(cart.getId()));
    }
}

