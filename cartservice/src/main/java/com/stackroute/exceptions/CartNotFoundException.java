package com.stackroute.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(int productID) {
        super(String.format("Cart with ID %d not found.", productID));
    }
}
