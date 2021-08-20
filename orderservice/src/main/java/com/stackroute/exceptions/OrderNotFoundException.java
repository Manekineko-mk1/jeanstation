package com.stackroute.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(int productID) {
        super(String.format("Order with ID %d not found.", productID));
    }
}
