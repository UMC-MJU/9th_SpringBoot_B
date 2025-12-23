package com.example.umc9th.domain.member.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD4001",
            "음식을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
    private final String code;
}
