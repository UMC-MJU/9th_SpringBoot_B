package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = (value > 0);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.INVALID_PAGE_NUM.getMessage()).addConstraintViolation();
        }

        return isValid;
    }
}
