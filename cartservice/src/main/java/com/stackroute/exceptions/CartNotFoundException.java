package com.stackroute.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String productID) {
        super(String.format("Cart with ID %d not found.", productID));
    }

    public CartNotFoundException() {
        super();
    }
}
