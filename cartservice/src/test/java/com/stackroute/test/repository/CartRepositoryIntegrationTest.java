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
    private ArrayList cartItems;

    @BeforeEach
    public void setUp() {
        cartItems = new ArrayList<String>();
        cart = new Cart();
        cart.setId("1l");
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
        Cart cart = new Cart(10, cartItems);
        Cart cart1 = new Cart(20, cartItems);
        cartRepository.save(cart);
        cartRepository.save(cart1);

        List<Cart> cartList = cartRepository.findAll();
        assertEquals(20, cartList.get(1).getPriceTotal());
    }

    @Test
    public void givenCartIdThenShouldReturnRespectiveCart() {
        Cart cart = new Cart(10, cartItems);
        Cart cart1 = cartRepository.save(cart);
        Optional<Cart> optional = cartRepository.findById(cart1.getId());
        assertEquals(cart1.getId(), optional.get().getId());
        assertEquals(cart1.getPriceTotal(), optional.get().getPriceTotal());
        assertEquals(cart1.getCartItems(), optional.get().getCartItems());
    }

    @Test
    public void givenCartIdToDeleteThenShouldReturnDeletedCart() {
        Cart cart = new Cart(20, cartItems);
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());
        Optional optional = cartRepository.findById(cart.getId());
        assertEquals(Optional.empty(), optional);
    }
    
}
