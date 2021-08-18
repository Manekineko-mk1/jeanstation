package com.stackroute.controller;

import com.stackroute.domain.Product;
import com.stackroute.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * save a new Product
     */
    @PostMapping("/product")
    @ApiOperation(value = "POST a new Product", notes = "Add a new Product entry to the Product database " +
            "using a provided JSON Product object. Returns the newly created entry " +
            "if the operation is a success.", response = Entity.class)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    /**
     * retrieve all Products
     */
    @GetMapping("/products")
    @ApiOperation(value = "GET all Products", notes = "GET all Product entries from the Product database. " +
            "Returns the result as a List of Product object in JSON format " +
            "if any entry is found.", response = Entity.class)
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    /**
     * retrieve Product by id
     */
    @GetMapping("product/{productId}")
    @ApiOperation(value = "GET a Product by ID", notes = "GET a Product entry from the Product database " +
            "by a provided Product ID. Returns a Product object if found.", response = Entity.class)
    public ResponseEntity<Product> getProductById(@PathVariable("productId") int productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.FOUND);
    }

    /**
     * delete Product by id
     */
    @DeleteMapping("product/{productId}")
    @ApiOperation(value = "DELETE an existing Product", notes = "Remove a Product entry from the Product database " +
            "by a provided Product ID. Returns the deleted Product object " +
            "if the operation is successful.", response = Entity.class)
    public ResponseEntity<Product> getProductAfterDeleting(@PathVariable("ProductId") int productId) {
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }

    /**
     * update Product
     */
    @PutMapping("product")
    @ApiOperation(value = "UPDATE an existing Product", notes = "Update an existing Product entry " +
            "from the Product database using a provided JSON Product object. " +
            "Returns the updated entry if the operation is a success.", response = Entity.class)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
