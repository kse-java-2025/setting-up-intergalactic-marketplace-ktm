package com.example.spacecatsmarket.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CosmicWordCheckValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CosmicWordCheck {
    String message() default "Product name must include at least one cosmic term (star, galaxy, comet, cosmic, space, nebula, planet, asteroid, meteor, stellar)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

