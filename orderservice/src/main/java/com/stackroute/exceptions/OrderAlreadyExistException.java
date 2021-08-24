package com.stackroute.exceptions;

public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(String productID) {
        super(String.format("Order with ID %d already exist.", productID));
    }
}
