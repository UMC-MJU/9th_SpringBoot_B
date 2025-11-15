package com.example.myapp.domain.temp.exception;

import com.example.myapp.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
