package com.stackroute.exceptions;

public class CartAlreadyExistException extends RuntimeException {
    public CartAlreadyExistException(String productID) {
        super(String.format("Cart with ID %d already exist.", productID));
    }

    public CartAlreadyExistException() {
        super();
    }
}
