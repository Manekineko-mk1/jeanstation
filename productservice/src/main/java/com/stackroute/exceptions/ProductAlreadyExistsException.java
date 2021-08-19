package com.stackroute.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String productID) {
        super(String.format("Product with ID %s already exists.", productID));
    }

    public ProductAlreadyExistsException() {
        super();
    }
}
