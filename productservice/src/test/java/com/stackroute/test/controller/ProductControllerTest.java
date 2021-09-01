package com.stackroute.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.ProductController;
import com.stackroute.domain.Money;
import com.stackroute.domain.Product;
import com.stackroute.enums.Currency;
import com.stackroute.exceptions.GlobalExceptionHandler;
import com.stackroute.exceptions.ProductAlreadyExistsException;
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
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).setControllerAdvice(new GlobalExceptionHandler()).build();
        ArrayList<String> categories = new ArrayList<>();
        product = new Product();
        product.setId("1l");
        product.setName("product1");
        product.setDescription("Description");
        product.setPicture("picture");
        product.setPrice(new Money(7500, Currency.CAD));
        product.setDiscount(15);
        product.setQuantity(20);
        product.setCategories(categories);
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
        mockMvc.perform(post("/api/v1/product/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(productService).saveProduct(any());
    }

    @Test
    public void givenGetAllProductsThenShouldReturnListOfAllProducts() throws Exception {
        when(productService.findAllProducts()).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/products")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andDo(MockMvcResultHandlers.print());
        verify(productService).findAllProducts();
        verify(productService, times(1)).findAllProducts();
    }

    @Test
    void givenProductIdThenShouldReturnRespectiveProduct() throws Exception {
        when(productService.findProductById(product.getId())).thenReturn(product);
        mockMvc.perform(get("/api/v1/product/product/1l"))
               .andExpect(MockMvcResultMatchers.status().isFound())
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenProductIdToDeleteThenShouldNotReturnDeletedProduct() throws Exception {
        when(productService.deleteProductById(product.getId())).thenReturn(product);
        mockMvc.perform(delete("/api/v1/product/product/1l"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenProductToUpdateThenShouldReturnUpdatedProduct() throws Exception {
        when(productService.updateProduct(any())).thenReturn(product);
        mockMvc.perform(put("/api/v1/product/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test POST with ProductAlreadyExistException
     * @throws ProductAlreadyExistsException
     */
    @Test
    public void givenProductAlreadyExistThenTryToSaveThenShouldThrowException() throws Exception {
        when(productService.saveProduct(any())).thenThrow(ProductAlreadyExistsException.class);

        mockMvc.perform(post("/api/v1/product/product")
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(product)))
               .andExpect(status().isConflict())
               .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test GET ProductByID with ProductNotFoundException
     * @throws ProductNotFoundException
     */
    @Test
    public void givenNoProductExistThenGetProductByIDShouldThrowException() throws Exception {
        when(productService.findProductById(any())).thenThrow(ProductNotFoundException.class);

        mockMvc.perform(get("/api/v1/product/product/1l"))
               .andExpect(MockMvcResultMatchers.status().isNotFound())
               .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test DELETE ProductByID with ProductNotFoundException
     * @throws ProductNotFoundException
     */
    @Test
    public void givenNoProductExistThenDeleteProductByIDShouldThrowException() throws Exception {
        when(productService.deleteProductById(any())).thenThrow(ProductNotFoundException.class);

        mockMvc.perform(delete("/api/v1/product/product/1l"))
               .andExpect(MockMvcResultMatchers.status().isNotFound())
               .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Test UPDATE Product with ProductNotFoundException
     * @throws ProductNotFoundException
     */
    @Test
    public void givenNoProductExistThenUpdateProductShouldThrowException() throws Exception {
        when(productService.updateProduct(any())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(put("/api/v1/product/product")
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(product)))
               .andExpect(status().isNotFound())
               .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

