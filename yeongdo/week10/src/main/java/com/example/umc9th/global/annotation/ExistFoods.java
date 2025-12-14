package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.FoodExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 사용자 어노테이션
@Constraint(validatedBy = FoodExistValidator.class) // FoodExistsValidator라는 클래스를 통해 @ExistFood가 붙은 대상을 검증
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) // 적용 범위
@Retention(RetentionPolicy.RUNTIME) // 생명주기
public @interface ExistFoods {
    // 디폴트 메시지 설정
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}