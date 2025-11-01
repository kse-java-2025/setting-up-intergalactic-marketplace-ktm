package com.example.spacecatsmarket.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CosmicWordCheckValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private static final List<String> COSMIC_TERMS = Arrays.asList(
            "star", "galaxy", "comet", "cosmic", "space", "nebula", 
            "planet", "asteroid", "meteor", "stellar", "solar", "lunar",
            "orbital", "intergalactic", "universe"
    );

    @Override
    public void initialize(CosmicWordCheck constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }

        String lowerCaseValue = value.toLowerCase();
        return COSMIC_TERMS.stream()
                .anyMatch(term -> lowerCaseValue.contains(term.toLowerCase()));
    }
}

