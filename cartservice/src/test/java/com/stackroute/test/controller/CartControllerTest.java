package com.stackroute.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.CartController;
import com.stackroute.domain.Cart;
import com.stackroute.domain.Product;
import com.stackroute.exceptions.CartAlreadyExistException;
import com.stackroute.exceptions.CartNotFoundException;
import com.stackroute.exceptions.GlobalExceptionHandler;
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
    private List<Product> productList;
    private List<Cart> cartList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).setControllerAdvice(new GlobalExceptionHandler()).build();

        cart = new Cart();
        cart.setId("1l");
        cart.setPriceTotalBeforeTax(420);
        cart.setPriceTotalAfterTax(460);
        productList = new ArrayList<>();
        productList.add(new Product());
        cart.setCartItems(productList);

        cartList = new ArrayList<>();
        cartList.add(cart);
    }

    @AfterEach
    public void tearDown() {
       cart = null;
       productList = null;
       cartList = null;
    }

//    @Test
//    public void givenCartToSaveThenShouldReturnSavedCart() throws Exception {
//        when(cartService.saveCart(cart)).thenReturn(cart);
//        mockMvc.perform(post("/api/v1/cart/cart")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(cart)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(cartService).saveCart(any());
//    }
//
//    @Test
//    public void givenGetAllCartsThenShouldReturnListOfAllCarts() throws Exception {
//        when(cartService.findAllCarts()).thenReturn(cartList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cart/carts")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cart)))
//                .andDo(MockMvcResultHandlers.print());
//        verify(cartService).findAllCarts();
//        verify(cartService, times(1)).findAllCarts();
//    }
//
//    @Test
//    void givenCartIdThenShouldReturnRespectiveCart() throws Exception {
//        when(cartService.findCartById(cart.getId())).thenReturn(cart);
//        mockMvc.perform(get("/api/v1/cart/cart/1l"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenCartIdToDeleteThenShouldNotReturnDeletedCart() throws Exception {
//        when(cartService.deleteCartById(cart.getId())).thenReturn(cart);
//        mockMvc.perform(delete("/api/v1/cart/cart/1l"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenCartToUpdateThenShouldReturnUpdatedCart() throws Exception {
//        when(cartService.updateCart(any())).thenReturn(cart);
//        mockMvc.perform(put("/api/v1/cart/cart")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(cart)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test POST with CartAlreadyExistException
//     * @throws CartAlreadyExistException
//     */
//    @Test
//    public void givenCartAlreadyExistThenTryToSaveThenShouldThrowException() throws Exception {
//        when(cartService.saveCart(any())).thenThrow(CartAlreadyExistException.class);
//
//        mockMvc.perform(post("/api/v1/cart/cart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(cart)))
//                .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test GET Cart by ID with CartNotFoundException
//     * @throws CartNotFoundException
//     */
//    @Test
//    public void givenNoCartExistThenGetCartByIDShouldThrowException() throws Exception {
//        when(cartService.findCartById(any())).thenThrow(CartNotFoundException.class);
//
//        mockMvc.perform(get("/api/v1/cart/cart/NonExistCartId"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test DELETE Cart by ID with CartNotFoundException
//     * @throws CartNotFoundException
//     */
//    @Test
//    public void givenNoCartExistThenDeleteCartByIDShouldThrowException() throws Exception {
//        when(cartService.deleteCartById(any())).thenThrow(CartNotFoundException.class);
//
//        mockMvc.perform(delete("/api/v1/cart/cart/NonExistCartId"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test UPDATE Cart with CartNotFoundException
//     * @throws CartNotFoundException
//     */
//    @Test
//    public void givenNoCartExistThenUpdateCartShouldThrowException() throws Exception {
//        when(cartService.updateCart(any())).thenThrow(CartNotFoundException.class);
//        mockMvc.perform(put("/api/v1/cart/cart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(cart)))
//                .andExpect(status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

