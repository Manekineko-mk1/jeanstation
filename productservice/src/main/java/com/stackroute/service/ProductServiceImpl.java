package com.stackroute.service;

import com.stackroute.domain.Product;

import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    /**
     * Constructor based Dependency injection to inject ProductRepository here
     */
    @Autowired
    ProductRepository productRepository;

    /**
     * Implementation of saveProduct method
     */
    @Override
    public Product saveProduct(Product product) {
        boolean isProductExist;

        if(product.getId() == null) {
            isProductExist = false;
        } else {
            isProductExist = productRepository.findById(product.getId()).isPresent();
        }

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isProductExist) {
            log.error("ERROR: Unable to add product. Product already existed in database | Product ID: {} | Product name: {} | Timestamp(EST): {}",
                    product.getId(), product.getProductName(), timeStamp);

            throw new ProductAlreadyExistsException(product.getId());
        } else {
            log.info("SUCCESS: Add a product to the \"products\" collection | Product ID: {} | Product name: {} | Timestamp(EST): {}",
                    product.getId(), product.getProductName(), timeStamp);

            return productRepository.save(product);
        }
    }

    /**
     * AbstractMethod to save a list of products
     *
     * @param products
     */
    @Override
    public List<Product> saveProducts(List<Product> products) {
        boolean isProductExist;

        for(Product product : products){
            if(product.getId() == null) {
                isProductExist = false;
            } else {
                isProductExist = productRepository.findById(product.getId()).isPresent();
            }

            ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
            String timeStamp = zonedDateTimeNow.format(formatter);

            if(isProductExist) {
                log.error("ERROR: Unable to add product. Product already existed in database | Product ID: {} | Product name: {} | Timestamp(EST): {}",
                        product.getId(), product.getProductName(), timeStamp);

                throw new ProductAlreadyExistsException(product.getId());
            } else {
                productRepository.save(product);

                log.info("SUCCESS: Add a product to the \"products\" collection | Product ID: {} | Product name: {} | Timestamp(EST): {}",
                        product.getId(), product.getProductName(), timeStamp);
            }
        }

        return products;
    }

    /**
     * Implementation of getAllProducts method
     */
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Implementation of getProductById method
     */
    @Override
    public Product findProductById(String id) {
        boolean isProductExist = productRepository.findById(id).isPresent();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isProductExist) {
            log.info("SUCCESS: Product found by ID | Product ID: {} | Timestamp(EST): {}", id, timeStamp);

            return productRepository.findById(id).get();
        } else {
            log.error("ERROR: Unable to find product. Product ID not found | Product ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new ProductNotFoundException(id);
        }
    }

    /**
     * Implementation of deleteProductById method
     */
    @Override
    public Product deleteProductById(String id) {
        Product product;
        Optional<Product> optional = productRepository.findById(id);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            product = productRepository.findById(id).get();
            productRepository.deleteById(id);

            log.info("SUCCESS: Deleted product by ID | Product ID: {} | Timestamp(EST): {}", id, timeStamp);

            return product;
        } else {
            log.error("ERROR: Unable to delete product. Product ID not found | Product ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new ProductNotFoundException(id);
        }
    }

    /**
     * Implementation of updateProduct method
     */
    @Override
    public Product updateProduct(Product product) {
        Product updatedProduct;
        Optional<Product> optional = productRepository.findById(product.getId());

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            // Locate the existing product with same product ID
            Product getProduct = productRepository.findById(product.getId()).get();

            // Update the existing product with new info
            getProduct.setProductName(product.getProductName());
            getProduct.setProductDescription(product.getProductDescription());
            getProduct.setPriceCAD(product.getPriceCAD());
            getProduct.setPicture(product.getPicture());
            getProduct.setProductCategories(product.getProductCategories());

            // Update the existing product to the DB
            productRepository.save(getProduct);

            // Retrieve the updated product for return
            updatedProduct = productRepository.findById(product.getId()).get();

            log.info("SUCCESS: Updated product to the \"products\" collection | Product ID: {} | Product name: {} | Timestamp(EST): {}",
                    product.getId(), product.getProductName(), timeStamp);

            return updatedProduct;
        } else {
            log.error("ERROR: Unable to delete product. Product ID not found | Product ID: {} | Timestamp(EST): {}", product.getId(), timeStamp);

            throw new ProductNotFoundException(product.getId());
        }
    }

}

