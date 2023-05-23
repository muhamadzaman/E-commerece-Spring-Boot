package com.ecommerece.commentservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Product not found on server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
