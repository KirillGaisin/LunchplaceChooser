package com.kgaisin.lunchchooser.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String exception) {
        super(exception);
    }
}
