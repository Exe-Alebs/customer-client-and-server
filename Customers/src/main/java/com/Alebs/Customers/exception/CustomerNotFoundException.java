package com.Alebs.Customers.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id) {
        super("Could not find the user with id :" + id);
    }
}
