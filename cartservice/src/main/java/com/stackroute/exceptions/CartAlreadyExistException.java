package com.stackroute.exceptions;

public class CartAlreadyExistException extends RuntimeException {
    public CartAlreadyExistException(int productID) {
        super(String.format("Cart with ID %d already exist.", productID));
    }
}
