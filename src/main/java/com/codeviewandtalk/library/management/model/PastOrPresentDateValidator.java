package com.codeviewandtalk.library.management.model;


import com.codeviewandtalk.library.management.exception.InvalidPublicationDateException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PastOrPresentDateValidator implements ConstraintValidator<PastOrPresentDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null checks if needed
        }
        if(value.isAfter(LocalDate.now())){
            throw new InvalidPublicationDateException("Publication date cannot be in the future: " + value);
        }
        return true;
    }
}
