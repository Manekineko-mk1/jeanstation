package com.stackroute.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productID) {
        super(String.format("Product with ID %s not found.", productID));
    }

    public ProductNotFoundException() {
        super();
    }
}
