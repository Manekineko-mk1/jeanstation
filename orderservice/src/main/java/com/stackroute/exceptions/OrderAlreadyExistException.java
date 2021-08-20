package com.stackroute.exceptions;

public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(int productID) {
        super(String.format("Order with ID %d already exist.", productID));
    }
}
