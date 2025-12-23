package com.example.umc9th.global.validation;

import com.example.umc9th.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;    // null 이면 다른 검증에서 처리
        }

        boolean isValid = value >= 1;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("page는 1 이상의 값이어야 합니다.")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
