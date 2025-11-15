package com.example.umc9th.domain.test.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    // TestException 생성자 호출
    public TestException(BaseErrorCode code) {
        super(code);
        // GeneralException 생성자 호출
        // RuntimeException 계열이라 언체크 예외로 처리
    }
}
