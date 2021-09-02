package com.stackroute.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String orderId) {
        super(String.format("Order with ID %s not found.", orderId));
    }
}
