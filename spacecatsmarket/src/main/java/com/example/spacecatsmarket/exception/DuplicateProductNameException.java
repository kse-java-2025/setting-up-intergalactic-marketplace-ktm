package com.example.spacecatsmarket.exception;

public class DuplicateProductNameException extends RuntimeException {

    public DuplicateProductNameException(String name) {
        super(String.format("Product with name '%s' already exists", name));
    }
}