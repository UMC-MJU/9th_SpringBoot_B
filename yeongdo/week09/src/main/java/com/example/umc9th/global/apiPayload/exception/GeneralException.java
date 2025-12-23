package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 프로젝트 Exception
@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{ // 언체크 예외 (throws 안 써도 됨)
    private final BaseErrorCode code;
}
