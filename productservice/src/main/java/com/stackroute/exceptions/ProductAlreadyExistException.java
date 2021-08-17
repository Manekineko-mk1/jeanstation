package com.stackroute.exceptions;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(int productID) {
        super(String.format("Product with ID %d already exist.", productID));
    }
}
