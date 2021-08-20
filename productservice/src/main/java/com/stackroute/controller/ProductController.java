package com.stackroute.controller;

import com.stackroute.domain.Product;
import com.stackroute.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/")
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {
    private ProductService productService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * save a new Product
     */
    @PostMapping("product")
    @ApiOperation(value = "POST a new Product", notes = "Add a new Product entry to the Product database " +
            "using a provided JSON Product object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Added a product to products collection | Product ID: {} | Product name: {} | Timestamp(EST): {}",
                product.getId(), product.getProductName(), timeStamp);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * save a new Product
     */
    @PostMapping("products")
    @ApiOperation(value = "POST a list of new Product", notes = "Add a list of new Product entries " +
            "to the Product database using a provided JSON Product object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<List<Product>> saveProducts(@RequestBody List<Product> products) {
        List<Product> savedProduct = productService.saveProducts(products);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Added the list of products to the products collection | Timestamp(EST): {}", timeStamp);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    /**
     * retrieve all Products
     */
    @GetMapping("products")
    @ApiOperation(value = "GET all Products", notes = "GET all Product entries from the Product database. " +
            "Returns the result as a List of Product object in JSON format " +
            "if any entry is found.", response = ResponseEntity.class)
    public ResponseEntity<List<Product>> getAllProducts() {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get all product entries | Timestamp: {}", timeStamp);

        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    /**
     * retrieve Product by id
     */
    @GetMapping("product/{productId}")
    @ApiOperation(value = "GET a Product by ID", notes = "GET a Product entry from the Product database " +
            "by a provided Product ID. Returns a Product object if found.", response = ResponseEntity.class)
    public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get a product | Product ID: {} | Timestamp: {}" ,productId ,timeStamp);

        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.FOUND);
    }

    /**
     * delete Product by id
     */
    @DeleteMapping("product/{productId}")
    @ApiOperation(value = "DELETE an existing Product", notes = "Remove a Product entry from the Product database " +
            "by a provided Product ID. Returns the deleted Product object " +
            "if the operation is successful.", response = ResponseEntity.class)
    public ResponseEntity<Product> getProductAfterDeleting(@PathVariable("productId") String productId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to DELETE a product | Product ID: {} | Timestamp: {}", productId,timeStamp);

        return new ResponseEntity<>(productService.deleteProductById(productId), HttpStatus.OK);
    }

    /**
     * update Product
     */
    @PutMapping("product")
    @ApiOperation(value = "UPDATE an existing Product", notes = "Update an existing Product entry " +
            "from the Product database using a provided JSON Product object. " +
            "Returns the updated entry if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to UPDATE a product | Product Name: {} | Product ID: {} | Timestamp: {}",
                product.getProductName() ,product.getId() ,timeStamp);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
