package com.cassiomolin.example.shoppinglist.service;

/**
 * Exception to indicate that a product could not be found.
 *
 * @author cassiomolin
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
