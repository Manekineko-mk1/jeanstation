package com.stackroute.test.service;

import com.stackroute.domain.Cart;
import com.stackroute.repository.CartRepository;
import com.stackroute.service.CartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CartServiceIntegrationTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartService;
    private Cart cart1, cart2, cart3;
    private List<Cart> cartList;
    private Optional optional;

    @BeforeEach
    public void setUp() {

        cartList = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        categories.add("cat1");
        categories.add("cat2");
        cart1 = new Cart("Cart1", "description1", "picture1", 42, 10, 10, categories);
        cart2 = new Cart("Cart2", "description2", "picture2", 43, 10, 10, categories);
        cart3 = new Cart("Cart3", "description3", "picture3", 44, 10, 10, categories);
        cartList.add(cart1);
        cartList.add(cart2);
        cartList.add(cart3);
    }

    @AfterEach
    public void tearDown() {
        cart1 = cart2 = cart3 = null;
        cartList = null;
    }

    @Test
    public void givenCartToSaveThenShouldReturnSavedCart() {
        Cart savedCart = cartRepository.save(cart1);
        assertNotNull(savedCart);
        assertEquals(cart1.getId(), savedCart.getId());
    }

    @Test
    public void givenGetAllCartsThenShouldReturnListOfAllCarts() {
        List<Cart> cartList = (List<Cart>) cartRepository.findAll();
        assertNotNull(cartList);
    }
}