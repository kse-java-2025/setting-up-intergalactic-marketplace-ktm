package com.example.spacecatsmarket.exception;

public class DuplicateProductNameException extends RuntimeException {

    public DuplicateProductNameException(String name) {
        super("Product with name '" + name + "' already exists");
    }
}