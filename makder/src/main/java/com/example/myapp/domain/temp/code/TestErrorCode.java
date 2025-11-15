package com.example.myapp.domain.temp.code;

import com.example.myapp.global.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    TEST_EXCEPTION("TEMP4001", "테스트 예외입니다.");

    private final String code;
    private final String message;
}
