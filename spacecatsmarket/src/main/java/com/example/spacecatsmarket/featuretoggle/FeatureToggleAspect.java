package com.example.spacecatsmarket.featuretoggle;

import com.example.spacecatsmarket.exception.FeatureNotAvailableException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    public FeatureToggleAspect(FeatureToggleService featureToggleService) {
        this.featureToggleService = featureToggleService;
    }

    @Before("@annotation(com.example.spacecatsmarket.featuretoggle.FeatureToggle)")
    public void checkFeatureToggle(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        FeatureToggle featureToggle = method.getAnnotation(FeatureToggle.class);

        if (featureToggle != null) {
            String featureName = featureToggle.featureName();
            boolean isEnabled = featureToggleService.isFeatureEnabled(featureName);

            if (!isEnabled) {
                throw new FeatureNotAvailableException(featureName);
            }
        }
    }
}

