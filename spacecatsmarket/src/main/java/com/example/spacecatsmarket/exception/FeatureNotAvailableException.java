package com.example.spacecatsmarket.exception;

public class FeatureNotAvailableException extends RuntimeException {

    public FeatureNotAvailableException(String featureName) {
        super("Feature '" + featureName + "' is not available");
    }
}

