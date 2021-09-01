package com.stackroute.service;

import com.stackroute.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * AbstractMethod to save a product
     */
    Product saveProduct(Product product);

    /**
     * AbstractMethod to save a list of products
     */
    List<Product> saveProducts(List<Product> products);

    /**
     * AbstractMethod to get all products
     */
    List<Product> findAllProducts();

    /**
     * AbstractMethod to get product by id
     */
    Product findProductById(String id);

    /**
     * AbstractMethod to delete product by id
     */
    Product deleteProductById(String id);

    /**
     * AbstractMethod to update a product
     */
    Product updateProduct(Product product);
}
