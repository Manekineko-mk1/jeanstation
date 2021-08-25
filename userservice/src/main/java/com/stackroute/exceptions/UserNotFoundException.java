package com.stackroute.exceptions;

public class UserNotFoundException extends RuntimeException{
//    public UserNotFoundException(String userID) {
//        super(String.format("User with ID %s not found.", userID));
//    }
    private String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message=message;
    }

    public UserNotFoundException() {
        super();
    }
}
