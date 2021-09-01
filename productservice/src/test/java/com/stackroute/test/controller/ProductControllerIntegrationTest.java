package com.stackroute.test.controller;

import com.stackroute.domain.Money;
import com.stackroute.domain.Product;
import com.stackroute.enums.Currency;
import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductControllerIntegrationTest {
    @Autowired
    private ProductService productService;
    private Product product;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("cat1");
        categories.add("cat2");
        Money money1 = new Money(1000, Currency.CAD);
        product = new Product("Product1", "description1", "picture1", money1, 10, 10, categories);
        productList = new ArrayList<Product>();
        productList.add(product);
    }

    @AfterEach
    void tearDown() {
        product = null;
    }

    @Test
    void givenProductToSaveThenShouldReturnTheSavedProduct() throws ProductAlreadyExistsException {
        Product savedProduct = productService.saveProduct(product);
        assertNotNull(savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
    }

    @Test
    void givenProductToSaveThenThrowException() throws ProductAlreadyExistsException {
        assertNotNull(productService.saveProduct(product));
        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProduct(product));
    }

    @Test
    void givenProductToDeleteThenShouldReturnTheDeletedProduct() throws ProductNotFoundException {
        assertNotNull(productService.saveProduct(product));
        Product deletedBlog = productService.deleteProductById(product.getId());
        assertNotNull(deletedBlog);
    }

    @Test
    void givenProductToDeleteThenThrowException() throws ProductNotFoundException {
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(product.getId()));
    }

    @Test
    void givenCallToGetAllProductsThenListShouldNotBeNull() throws Exception {
        List<Product> retrievedProducts = productService.findAllProducts();
        assertNotNull(retrievedProducts);
    }

    @Test
    void givenProductToUpdateThenShouldReturnUpdatedProduct() throws ProductNotFoundException {
        Product savedProduct = productService.saveProduct(product);
        savedProduct.setDescription("update content");
        Product updatedBlog = productService.updateProduct(savedProduct);
        assertNotNull(updatedBlog);
        assertEquals("update content", updatedBlog.getDescription());
    }

    @Test
    void givenProductToUpdateThenThrowException() throws ProductNotFoundException {
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(product));
    }

    @Test
    void givenProductIdThenShouldReturnRespectiveProduct() throws ProductNotFoundException {
        assertNotNull(productService.saveProduct(product));
        Product retrievedBlog = productService.findProductById(product.getId());
        assertNotNull(retrievedBlog);
    }

    @Test
    void givenProductIdThenShouldThrowException() throws ProductNotFoundException {
        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(product.getId()));
    }
}

