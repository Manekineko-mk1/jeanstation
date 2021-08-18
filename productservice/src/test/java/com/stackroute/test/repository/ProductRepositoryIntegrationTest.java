package com.stackroute.test.repository;

import com.stackroute.domain.Product;
import com.stackroute.repository.ProductRepository;
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
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;
    private Product product;
    private ArrayList categories;

    @BeforeEach
    public void setUp() {
        categories = new ArrayList();
        product = new Product();
        product.setId("1l");
        product.setProductName("product1");
        product.setProductDescription("Description");
        product.setPicture("picture");
        product.setPriceCAD(10.00f);
        product.setDiscount(0.1f);
        product.setQuantity(20);
        product.setProductCategories(categories);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }

    @Test
    public void givenProductToSaveThenShouldReturnSavedProduct() {
        productRepository.save(product);
        Product fetchedproduct = productRepository.findById(product.getId()).get();
        assertEquals("1", fetchedproduct.getId());
    }


    @Test
    public void givenGetAllProductsThenShouldReturnListOfAllProducts() {
        Product product = new Product("product2", "description", "picture", 10.99f, 0.0f, 10, categories);
        Product product1 = new Product("product3", "description", "picture", 1.99f, 0.05f, 100, categories);
        productRepository.save(product);
        productRepository.save(product1);

        List<Product> productList = productRepository.findAll();
        assertEquals("product3", productList.get(1).getProductName());
    }

    @Test
    public void givenProductIdThenShouldReturnRespectiveProduct() {
        Product product = new Product("product9", "description", "picture", 5.50f, 0.25f, 56, categories);
        Product product1 = productRepository.save(product);
        Optional<Product> optional = productRepository.findById(product1.getId());
        assertEquals(product1.getId(), optional.get().getId());
        assertEquals(product1.getProductName(), optional.get().getProductName());
        assertEquals(product1.getProductDescription(), optional.get().getProductDescription());
        assertEquals(product1.getPriceCAD(), optional.get().getPriceCAD());
        assertEquals(product1.getDiscount(), optional.get().getDiscount());
        assertEquals(product1.getQuantity(), optional.get().getQuantity());
    }

    @Test
    public void givenProductIdToDeleteThenShouldReturnDeletedProduct() {
        Product product = new Product("product4", "description", "picture", 2.31f, 0.01f, 23, categories);
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        Optional optional = productRepository.findById(product.getId());
        assertEquals(Optional.empty(), optional);
    }
    
}
