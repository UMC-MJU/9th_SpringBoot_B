package com.example.myapp.global.exception.handler;

import com.example.myapp.domain.temp.exception.TestException;
import com.example.myapp.global.api.ApiResponse;
import com.example.myapp.global.exception.BaseErrorCode;
import com.example.myapp.global.exception.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionAdvice {

    // TestException 핸들러
    @ExceptionHandler(value = TestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleTestException(TestException e) {
        BaseErrorCode code = e.getErrorCode();
        log.warn("Test Exception: {}", code.getMessage());
        return ApiResponse.onFailure(code, null);
    }

    // 일반적인 서버 에러 핸들러
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleException(Exception e) {
        log.error("Server Error: ", e);
        return ApiResponse.onFailure(GeneralErrorCode._INTERNAL_SERVER_ERROR, null);
    }
}
