package com.MyGreetingApp.backend;

public class GreetingNotFoundException extends RuntimeException {
    public GreetingNotFoundException(Long id) {
        super("Greeting not found with id: " + id);
    }
}

