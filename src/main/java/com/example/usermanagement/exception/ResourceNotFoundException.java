package com.example.usermanagement.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String id) {
        super("Could not find user " + id);
    }
}
