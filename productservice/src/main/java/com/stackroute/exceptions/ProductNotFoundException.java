package com.stackroute.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(int productID) {
        super(String.format("Product with ID %d not found.", productID));
    }
}
