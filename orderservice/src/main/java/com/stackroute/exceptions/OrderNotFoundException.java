package com.stackroute.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String orderId) {
        super(String.format("Order with ID %d not found.", orderId));
    }
}
