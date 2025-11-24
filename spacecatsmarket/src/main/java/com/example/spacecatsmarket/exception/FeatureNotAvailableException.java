package com.example.spacecatsmarket.exception;

public class FeatureNotAvailableException extends RuntimeException {

    public FeatureNotAvailableException(String featureName) {
        super(String.format("Feature '%s' is not available", featureName));
    }
}

