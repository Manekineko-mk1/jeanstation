package com.stackroute.service;

import com.stackroute.domain.Product;
import java.util.List;

public interface ProductService {
    /**
     * AbstractMethod to save a product
     */
    Product saveProduct(Product product);

    /**
     * AbstractMethod to get all products
     */
    List<Product> getAllProducts();

    /**
     * AbstractMethod to get product by id
     */
    Product getProductById(String id);

    /**
     * AbstractMethod to delete product by id
     */
    Product deleteProduct(String id);

    /**
     * AbstractMethod to update a product
     */
    Product updateProduct(Product product);
}
