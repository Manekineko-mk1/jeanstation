package com.stackroute.service;

import com.stackroute.domain.Product;

import com.stackroute.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    /**
     * Constructor based Dependency injection to inject ProductRepository here
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Implementation of saveProduct method
     */
    @Override
    public Product saveProduct(Product product) {
        boolean isProductExist = productRepository.findById(product.getId()).isPresent();
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    /**
     * Implementation of getAllProducts method
     */
    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Implementation of getProductById method
     */
    @Override
    public Product getProductById(String id) {
        Product product = null;
        product = productRepository.findById(id).get();
        return product;
    }

    /**
     * Implementation of deleteProductById method
     */
    @Override
    public Product deleteProduct(String id) {
        Product product = null;
        Optional optional = productRepository.findById(id);
        if (optional.isPresent()) {
            product = productRepository.findById(id).get();
            productRepository.deleteById(id);
        }
        return product;
    }

    /**
     * Implementation of updateProduct method
     */
    @Override
    public Product updateProduct(Product product) {
        Product updatedProduct = null;
        Optional optional = productRepository.findById(product.getId());
        if (optional.isPresent()) {
            Product getProduct = productRepository.findById(product.getId()).get();
            getProduct.setProductName(product.getProductName());
            getProduct.setProductDescription(product.getProductDescription());
            getProduct.setPriceCAD(product.getPriceCAD());
            getProduct.setPicture(product.getPicture());
            getProduct.setProductCategories(product.getProductCategories());
            updatedProduct = saveProduct(getProduct);
            System.out.println(updatedProduct);
        }
        return updatedProduct;
    }
}

