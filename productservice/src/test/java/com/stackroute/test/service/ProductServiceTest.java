package com.stackroute.test.service;

import com.stackroute.domain.Money;
import com.stackroute.domain.Product;

import com.stackroute.enums.Currency;
import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.repository.ProductRepository;
import com.stackroute.service.ProductServiceImpl;
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
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;
    private Product product, product1;
    private List<Product> productList;
    private Optional<Product> optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<String> categories = new ArrayList<>();
        categories.add("cat1");
        categories.add("cat2");
        Money money1 = new Money(1000, Currency.CAD);
        product = new Product("Product1", "description1", "picture1", money1, 10, 10, categories);
        product1 = new Product("Product2", "description2", "picture2", money1, 10, 10, categories);
        product.setId("productId");
        product1.setId("product1Id");
        optional = Optional.of(product);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }

    @Test
    public void givenProductToSaveThenShouldReturnSavedProduct() throws ProductAlreadyExistsException {
        when(productRepository.save(any())).thenReturn(product);
        assertEquals(product, productService.saveProduct(product));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void givenProductToSaveThenShouldNotReturnSavedProduct() throws ProductAlreadyExistsException {
        when(productRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(product);
        });
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void getAllProductsThenShouldReturnListOfAllProducts() throws ProductNotFoundException {
        productRepository.save(product);
        //stubbing the mock to return specific data
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 = productService.findAllProducts();
        assertEquals(productList, productList1);
        verify(productRepository, times(1)).save(product);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void givenProductIdThenShouldReturnRespectiveProduct() throws ProductNotFoundException {
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        productService.findProductById(product.getId());
        verify(productRepository, times(2)).findById(any());
    }

    @Test
    void givenProductIdToDeleteThenShouldReturnDeletedProduct() throws ProductNotFoundException {
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        Product deletedProduct = productService.deleteProductById(product.getId());
        assertEquals(product.getId(), deletedProduct.getId());

        verify(productRepository, times(2)).findById(product.getId());
        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    void givenProductIdToDeleteThenShouldNotReturnDeletedProduct() throws ProductNotFoundException {
        when(productRepository.findById(any())).thenThrow(ProductNotFoundException.class);
        Assertions.assertThrows(ProductNotFoundException.class, () ->
                productService.deleteProductById(any()));

        verify(productRepository, times(1)).findById(any());
    }

    @Test
    public void givenProductToUpdateThenShouldReturnUpdatedProduct() throws ProductNotFoundException {
        when(productRepository.findById(product.getId())).thenReturn(optional);
        when(productRepository.save(product)).thenReturn(product);

        product.setDescription("SampleProductForTesting");
        Product product1 = productService.updateProduct(product);
        assertEquals(product1.getDescription(), "SampleProductForTesting");

        verify(productRepository, times(1)).save(product);
        verify(productRepository, times(3)).findById(product.getId());
    }

    @Test
    public void givenProductToUpdateThenShouldNotReturnUpdatedProduct() throws ProductNotFoundException {
        when(productRepository.findById(product.getId())).thenThrow(ProductNotFoundException.class);
        Assertions.assertThrows(ProductNotFoundException.class, () ->
                productService.updateProduct(product));

        verify(productRepository, times(1)).findById(product.getId());
    }
}