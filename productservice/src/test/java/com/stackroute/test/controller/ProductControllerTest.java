package com.stackroute.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.ProductController;
import com.stackroute.domain.Product;
import com.stackroute.exceptions.ProductAlreadyExistException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.service.ProductService;
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
public class ProductControllerTest {

    private MockMvc mockMvc;
    @Mock
    ProductService productService;
    @InjectMocks
    private ProductController productController;
    private Product product;
    private List<Product> productList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        ArrayList categories = new ArrayList();
        product = new Product();
        product.setId(1l);
        product.setProductName("product1");
        product.setProductDescription("Description");
        product.setPicture("picture");
        product.setPriceCAD(10.00f);
        product.setDiscount(0.1f);
        product.setQuantity(20);
        product.setProductCategories(categories);
        productList = new ArrayList<>();
        productList.add(product);
    }

    @AfterEach
    public void tearDown() {
       product = null;
    }

    @Test
    public void givenProductToSaveThenShouldReturnSavedProduct() throws Exception {
        when(productService.saveProduct(any())).thenReturn(product);
        mockMvc.perform(post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(productService).saveProduct(any());
    }

    @Test
    public void givenGetAllProductsThenShouldReturnListOfAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andDo(MockMvcResultHandlers.print());
        verify(productService).getAllProducts();
        verify(productService, times(1)).getAllProducts();

    }

    @Test
    void givenProductIdThenShouldReturnRespectiveProduct() throws Exception {
        when(productService.getProductById(product.getId().intValue())).thenReturn(product);
        mockMvc.perform(get("/api/v1/product/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenProductIdToDeleteThenShouldNotReturnDeletedProduct() throws Exception {
        when(productService.deleteProduct(product.getId().intValue())).thenReturn(product);
        mockMvc.perform(delete("/api/v1/product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenProductToUpdateThenShouldReturnUpdatedProduct() throws Exception {
        when(productService.updateProduct(any())).thenReturn(product);
        mockMvc.perform(put("/api/v1/product").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenProductAlreadyExistToSaveThenShouldThrowException() throws ProductAlreadyExistException {

    }

    @Test
    public void givenProductIdNotExistThenShouldThrowException() throws ProductNotFoundException {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

