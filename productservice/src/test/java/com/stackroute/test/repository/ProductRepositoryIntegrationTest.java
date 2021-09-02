//package com.stackroute.test.repository;
//
//import com.stackroute.domain.Money;
//import com.stackroute.domain.Product;
//import com.stackroute.enums.Currency;
//import com.stackroute.repository.ProductRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ProductRepositoryIntegrationTest {
//
//    @Autowired
//    private ProductRepository productRepository;
//    private Product product;
//    private ArrayList categories;
//
//    @BeforeEach
//    public void setUp() {
//        categories = new ArrayList<String>();
//        product = new Product();
//        product.setId("1l");
//        product.setName("product1");
//        product.setDescription("Description");
//        product.setPicture("picture");
//        product.setPrice(new Money(1000, Currency.CAD));
//        product.setDiscount(20);
//        product.setQuantity(20);
//        product.setCategories(categories);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        productRepository.deleteAll();
//        product = null;
//    }
//
//    @Test
//    public void givenProductToSaveThenShouldReturnSavedProduct() {
//        productRepository.save(product);
//        Product fetchedproduct = productRepository.findById(product.getId()).get();
//        assertEquals("1", fetchedproduct.getId());
//    }
//
//
//    @Test
//    public void givenGetAllProductsThenShouldReturnListOfAllProducts() {
//        Money money1 = new Money(1000, Currency.CAD);
//        Money money2 = new Money(1750, Currency.USD);
//        Product product = new Product("product2", "description", "picture", money1, 20, 10, categories);
//        Product product1 = new Product("product3", "description", "picture", money2, 20, 100, categories);
//        productRepository.save(product);
//        productRepository.save(product1);
//
//        List<Product> productList = productRepository.findAll();
//        assertEquals("product3", productList.get(1).getName());
//    }
//
//    @Test
//    public void givenProductIdThenShouldReturnRespectiveProduct() {
//        Money money1 = new Money(1000, Currency.CAD);
//        Product product = new Product("product9", "description", "picture", money1, 25, 56, categories);
//        Product product1 = productRepository.save(product);
//        Optional<Product> optional = productRepository.findById(product1.getId());
//        assertEquals(product1.getId(), optional.get().getId());
//        assertEquals(product1.getName(), optional.get().getName());
//        assertEquals(product1.getDescription(), optional.get().getDescription());
//        assertEquals(product1.getPrice(), optional.get().getPrice());
//        assertEquals(product1.getDiscount(), optional.get().getDiscount());
//        assertEquals(product1.getQuantity(), optional.get().getQuantity());
//    }
//
//    @Test
//    public void givenProductIdToDeleteThenShouldReturnDeletedProduct() {
//        Money money1 = new Money(1000, Currency.CAD);
//        Product product = new Product("product4", "description", "picture", money1, 20, 23, categories);
//        productRepository.save(product);
//        productRepository.deleteById(product.getId());
//        Optional optional = productRepository.findById(product.getId());
//        assertEquals(Optional.empty(), optional);
//    }
//
//}
