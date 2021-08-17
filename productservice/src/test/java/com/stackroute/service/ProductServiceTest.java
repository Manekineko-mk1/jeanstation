package com.stackroute.service;

import com.stackroute.domain.Product;

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
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        product = new Product(1, "SampleProduct", "Imneet", "SampleProductforTesting");
        product1 = new Product(2, "Product 1", "John", "Sample Product 1 for Testing");
        optional = Optional.of(product);
    }

    @AfterEach
    public void tearDown() {
        product = null;
    }

    @Test
    public void givenProductToSaveThenShouldReturnSavedProduct() {
        when(productRepository.save(any())).thenReturn(product);
        assertEquals(product, productService.saveProduct(product));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void givenProductToSaveThenShouldNotReturnSavedProduct() {
        when(productRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(product);
        });
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void getAllProducts() {
        productRepository.save(product);
        //stubbing the mock to return specific data
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 = productService.getAllProducts();
        assertEquals(productList, productList1);
        verify(productRepository, times(1)).save(product);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void givenProductIdThenShouldReturnRespectiveProduct() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        Product retrievedProduct = productService.getProductById(product.getProductId());
        verify(productRepository, times(1)).findById(anyInt());

    }

    @Test
    void givenProductIdToDeleteThenShouldReturnDeletedProduct() {
        when(productRepository.findById(product.getProductId())).thenReturn(optional);
        Product deletedProduct = productService.deleteProduct(1);
        assertEquals(1, deletedProduct.getProductId());

        verify(productRepository, times(2)).findById(product.getProductId());
        verify(productRepository, times(1)).deleteById(product.getProductId());
    }

    @Test
    void givenProductIdToDeleteThenShouldNotReturnDeletedProduct() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
        Product deletedProduct = productService.deleteProduct(1);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    public void givenProductToUpdateThenShouldReturnUpdatedProduct() {
        when(productRepository.findById(product.getProductId())).thenReturn(optional);
        when(productRepository.save(product)).thenReturn(product);
        product.setProductContent("SampleProductforTesting");
        Product product1 = productService.updateProduct(product);
        assertEquals(product1.getProductContent(), "SampleProductforTesting");
        verify(productRepository, times(1)).save(product);
        verify(productRepository, times(2)).findById(product.getProductId());
    }
}