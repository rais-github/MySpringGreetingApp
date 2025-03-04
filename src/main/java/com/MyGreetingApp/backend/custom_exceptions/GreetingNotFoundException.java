package com.MyGreetingApp.backend.custom_exceptions;

public class GreetingNotFoundException extends RuntimeException {
    public GreetingNotFoundException(Long id) {
        super("Greeting not found with id: " + id);
    }
}

