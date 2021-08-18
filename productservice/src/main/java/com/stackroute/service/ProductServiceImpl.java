package com.stackroute.service;

import com.stackroute.domain.Product;

import com.stackroute.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

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
        return productRepository.save(product);
    }

    /**
     * Implementation of getAllProducts method
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Implementation of getProductById method
     */
    @Override
    public Product getProductById(String id) {
        Product product;
        product = productRepository.findById(id).get();
        return product;
    }

    /**
     * Implementation of deleteProductById method
     */
    @Override
    public Product deleteProduct(String id) {
        Product product = null;
        Optional<Product> optional = productRepository.findById(id);
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
        Optional<Product> optional = productRepository.findById(product.getId());
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

