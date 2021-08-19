package com.stackroute.test.controller;

import com.stackroute.domain.Product;
import com.stackroute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class ProductControllerIntegrationTest {

    private final ProductService productService;

    @Autowired
    public ProductControllerIntegrationTest(ProductService productService) {
        this.productService = productService;
    }


    /**
     * save a new Product
     */
    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    /**
     * retrieve all Products
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);

    }

    /**
     * retrieve Product by id
     */
    @GetMapping("product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.FOUND);
    }

    /**
     * delete Product by id
     */
    @DeleteMapping("product/{productId}")
    public ResponseEntity<Product> getProductAfterDeleting(@PathVariable("ProductId") String productId) {
        return new ResponseEntity<>(productService.deleteProductById(productId), HttpStatus.OK);
    }

    /**
     * update Product
     */
    @PutMapping("product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
    
}

