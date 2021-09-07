package com.stackroute.exceptions;

public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(String orderId) {
        super(String.format("Order with ID %s already exist.", orderId));
    }
}
