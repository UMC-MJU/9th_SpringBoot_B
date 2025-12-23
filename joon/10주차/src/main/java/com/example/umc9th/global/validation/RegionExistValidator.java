package com.example.umc9th.global.validation;

import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.global.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;

        boolean exists = regionRepository.existsById(value);

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StoreErrorCode.REGION_NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return exists;
    }
}
