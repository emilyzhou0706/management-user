package com.example.usermanagement.exception;

public class UserNotFoundException extends Exception  {
    public UserNotFoundException(String id) {
        super("Could not find user " + id);
    }
}