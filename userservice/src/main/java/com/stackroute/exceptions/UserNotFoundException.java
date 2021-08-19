package com.stackroute.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userID) {
        super(String.format("User with ID %s not found.", userID));
    }

    public UserNotFoundException() {
        super();
    }
}
