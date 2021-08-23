package com.stackroute.test.service;

import com.stackroute.domain.Cart;

import com.stackroute.domain.Product;
import com.stackroute.repository.CartRepository;
import com.stackroute.service.CartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;
    private Cart cart, cart1;
    private List<Cart> cartList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<String> categories = new ArrayList<>();
        categories.add("cat1");
        categories.add("cat2");
        ArrayList<Product> itemsList = new ArrayList<>();
        itemsList.add(new Product("1L", "Product1", "description1", "picture1", 42, 10, 10, "L", "BLUE"));
        cart = new Cart(10, itemsList);
        cart1 = new Cart(20, itemsList);
        optional = Optional.of(cart);
    }

    @AfterEach
    public void tearDown() {
        cart = null;
    }

    @Test
    public void givenCartToSaveThenShouldReturnSavedCart() {
        when(cartRepository.save(any())).thenReturn(cart);
        assertEquals(cart, cartService.saveCart(cart));
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    public void givenCartToSaveThenShouldNotReturnSavedCart() {
        when(cartRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            cartService.saveCart(cart);
        });
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    public void getAllCarts() {
        cartRepository.save(cart);
        //stubbing the mock to return specific data
        when(cartRepository.findAll()).thenReturn(cartList);
        List<Cart> cartList1 = cartService.findAllCarts();
        assertEquals(cartList, cartList1);
        verify(cartRepository, times(1)).save(cart);
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    public void givenCartIdThenShouldReturnRespectiveCart() {
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart));
        Cart retrievedCart = cartService.findCartById(cart.getId());
        verify(cartRepository, times(1)).findById(any());

    }

    @Test
    void givenCartIdToDeleteThenShouldReturnDeletedCart() {
        when(cartRepository.findById(cart.getId())).thenReturn(optional);
        Cart deletedCart = cartService.deleteCartById("1");
        assertEquals(1, deletedCart.getId());

        verify(cartRepository, times(2)).findById(cart.getId());
        verify(cartRepository, times(1)).deleteById(cart.getId());
    }

    @Test
    void givenCartIdToDeleteThenShouldNotReturnDeletedCart() {
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.empty());
        Cart deletedCart = cartService.deleteCartById("1");
        verify(cartRepository, times(1)).findById(cart.getId());
    }

    @Test
    public void givenCartToUpdateThenShouldReturnUpdatedCart() {
        when(cartRepository.findById(cart.getId())).thenReturn(optional);
        when(cartRepository.save(cart)).thenReturn(cart);
        ArrayList<String> categories = new ArrayList<>();
        categories.add("cat3");
        categories.add("cat4");
        ArrayList<Product> itemsList = new ArrayList<>();
        itemsList.add(new Product("1L", "Product1", "description1", "picture1", 42, 10, 10, "S", "LIGHT_BLUE"));
        cart.setCartItems(itemsList);
        Cart cart1 = cartService.updateCart(cart);
        assertEquals(cart1.getCartItems(), categories);
        verify(cartRepository, times(1)).save(cart);
        verify(cartRepository, times(2)).findById(cart.getId());
    }
}