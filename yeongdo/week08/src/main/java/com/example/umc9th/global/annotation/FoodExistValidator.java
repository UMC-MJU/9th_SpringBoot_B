package com.example.umc9th.global.annotation;

import com.example.umc9th.domain.member.code.FoodErrorCode;
import com.example.umc9th.domain.member.repository.FoodRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> { //ExistFoods 어노테이션에 대한 로직, 검증 대상은 List<Long>

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> foodRepository.existsById(value)); // 모든 값이 존재하면 true

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;
    }

}