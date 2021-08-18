package com.stackroute.test.repository;

import com.stackroute.domain.Cart;
import com.stackroute.repository.CartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartRepositoryIntegrationTest {

    @Autowired
    private CartRepository cartRepository;
    private Cart cart;
    private ArrayList categories;

    @BeforeEach
    public void setUp() {
        categories = new ArrayList<String>();
        cart = new Cart();
        cart.setId("1l");
        cart.setCartName("cart1");
        cart.setCartDescription("Description");
        cart.setPicture("picture");
        cart.setPriceCAD(10.00f);
        cart.setDiscount(0.1f);
        cart.setQuantity(20);
        cart.setCartCategories(categories);
    }

    @AfterEach
    public void tearDown() {
        cartRepository.deleteAll();
        cart = null;
    }

    @Test
    public void givenCartToSaveThenShouldReturnSavedCart() {
        cartRepository.save(cart);
        Cart fetchedcart = cartRepository.findById(cart.getId()).get();
        assertEquals("1", fetchedcart.getId());
    }


    @Test
    public void givenGetAllCartsThenShouldReturnListOfAllCarts() {
        Cart cart = new Cart("cart2", "description", "picture", 10.99f, 0.0f, 10, categories);
        Cart cart1 = new Cart("cart3", "description", "picture", 1.99f, 0.05f, 100, categories);
        cartRepository.save(cart);
        cartRepository.save(cart1);

        List<Cart> cartList = cartRepository.findAll();
        assertEquals("cart3", cartList.get(1).getCartName());
    }

    @Test
    public void givenCartIdThenShouldReturnRespectiveCart() {
        Cart cart = new Cart("cart9", "description", "picture", 5.50f, 0.25f, 56, categories);
        Cart cart1 = cartRepository.save(cart);
        Optional<Cart> optional = cartRepository.findById(cart1.getId());
        assertEquals(cart1.getId(), optional.get().getId());
        assertEquals(cart1.getCartName(), optional.get().getCartName());
        assertEquals(cart1.getCartDescription(), optional.get().getCartDescription());
        assertEquals(cart1.getPriceCAD(), optional.get().getPriceCAD());
        assertEquals(cart1.getDiscount(), optional.get().getDiscount());
        assertEquals(cart1.getQuantity(), optional.get().getQuantity());
    }

    @Test
    public void givenCartIdToDeleteThenShouldReturnDeletedCart() {
        Cart cart = new Cart("cart4", "description", "picture", 2.31f, 0.01f, 23, categories);
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());
        Optional optional = cartRepository.findById(cart.getId());
        assertEquals(Optional.empty(), optional);
    }
    
}
