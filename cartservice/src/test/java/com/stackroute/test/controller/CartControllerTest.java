package com.stackroute.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.CartController;
import com.stackroute.domain.Cart;
import com.stackroute.exceptions.CartAlreadyExistException;
import com.stackroute.exceptions.CartNotFoundException;
import com.stackroute.service.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    private MockMvc mockMvc;
    @Mock
    CartService cartService;
    @InjectMocks
    private CartController cartController;
    private Cart cart;
    private List<Cart> cartList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
        ArrayList categories = new ArrayList();
        cart = new Cart();
        cart.setId("1l");
        cart.setCartName("cart1");
        cart.setCartDescription("Description");
        cart.setPicture("picture");
        cart.setPriceCAD(10.00f);
        cart.setDiscount(0.1f);
        cart.setQuantity(20);
        cart.setCartCategories(categories);
        cartList = new ArrayList<>();
        cartList.add(cart);
    }

    @AfterEach
    public void tearDown() {
       cart = null;
    }

    @Test
    public void givenCartToSaveThenShouldReturnSavedCart() throws Exception {
        when(cartService.saveCart(any())).thenReturn(cart);
        mockMvc.perform(post("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cart)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(cartService).saveCart(any());
    }

    @Test
    public void givenGetAllCartsThenShouldReturnListOfAllCarts() throws Exception {
        when(cartService.getAllCarts()).thenReturn(cartList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/carts")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cart)))
                .andDo(MockMvcResultHandlers.print());
        verify(cartService).getAllCarts();
        verify(cartService, times(1)).getAllCarts();

    }

    @Test
    void givenCartIdThenShouldReturnRespectiveCart() throws Exception {
        when(cartService.getCartById(cart.getId())).thenReturn(cart);
        mockMvc.perform(get("/api/v1/cart/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenCartIdToDeleteThenShouldNotReturnDeletedCart() throws Exception {
        when(cartService.deleteCart(cart.getId())).thenReturn(cart);
        mockMvc.perform(delete("/api/v1/cart/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenCartToUpdateThenShouldReturnUpdatedCart() throws Exception {
        when(cartService.updateCart(any())).thenReturn(cart);
        mockMvc.perform(put("/api/v1/cart").contentType(MediaType.APPLICATION_JSON).content(asJsonString(cart)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test POST with CartAlreadyExistException
     * @throws CartAlreadyExistException
     */
    @Test
    public void givenCartAlreadyExistThenTryToSaveThenShouldThrowException() throws CartAlreadyExistException {
//        when(cartService.saveCart(any())).thenReturn(cart);
//        mockMvc.perform(post("/api/v1/cart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(cart)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(cartService).saveCart(any());
    }

    /**
     * Test GET Cart by ID with CartNotFoundException
     * @throws CartNotFoundException
     */
    @Test
    public void givenNoCartExistThenGetCartByIDShouldThrowException() throws CartNotFoundException {

    }

    /**
     * Test DELETE Cart by ID with CartNotFoundException
     * @throws CartNotFoundException
     */
    @Test
    public void givenNoCartExistThenDeleteCartByIDShouldThrowException() throws CartNotFoundException {

    }

    /**
     * Test UPDATE Cart with CartNotFoundException
     * @throws CartNotFoundException
     */
    @Test
    public void givenNoCartExistThenUpdateCartShouldThrowException() throws CartNotFoundException {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

