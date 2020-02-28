package io.wootlab.micronaut.initializr.api;

import io.wootlab.micronaut.initializr.api.representation.ProjectSettingsRepresentation;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class SettingsValidator {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void validate(ProjectSettingsRepresentation settings) throws ValidationException {
        try {
            Set<ConstraintViolation<ProjectSettingsRepresentation>> violations = validator.validate(settings);
            if (violations.size() > 0) {
                String validationErrors = violations.stream()
                        .map(
                                ConstraintViolation::getMessage
                        ).collect(Collectors.joining(", "));
                throw new ValidationException(validationErrors);
            }
        }catch(IllegalArgumentException e){
            throw new ValidationException("Sent object can't be null ! " + e.getMessage(), e.getCause());
        }
    }
}