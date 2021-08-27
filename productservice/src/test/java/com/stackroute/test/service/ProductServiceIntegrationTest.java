package com.stackroute.test.service;

import com.stackroute.domain.Product;
import com.stackroute.repository.ProductRepository;
import com.stackroute.service.ProductServiceImpl;
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
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;
    private Product product1, product2, product3;
    private List<Product> productList;
    private Optional optional;

    @BeforeEach
    public void setUp() {

        productList = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        categories.add("cat1");
        categories.add("cat2");
        product1 = new Product("Product1", "description1", 42, 10, 10, categories);
        product2 = new Product("Product2", "description2", 43, 10, 10, categories);
        product3 = new Product("Product3", "description3", 44, 10, 10, categories);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
    }

    @AfterEach
    public void tearDown() {
        product1 = product2 = product3 = null;
        productList = null;
    }

    @Test
    public void givenProductToSaveThenShouldReturnSavedProduct() {
        Product savedProduct = productRepository.save(product1);
        assertNotNull(savedProduct);
        assertEquals(product1.getId(), savedProduct.getId());
    }

    @Test
    public void givenGetAllProductsThenShouldReturnListOfAllProducts() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        assertNotNull(productList);
    }
}