package com.stackroute.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userID) {
        super(String.format("User with ID %s already exists.", userID));
    }

    public UserAlreadyExistsException() {
        super();
    }
}
