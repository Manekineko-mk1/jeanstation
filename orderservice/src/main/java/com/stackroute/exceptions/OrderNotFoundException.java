package com.stackroute.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String productID) {
        super(String.format("Order with ID %d not found.", productID));
    }
}
