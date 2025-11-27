package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "SUCCESS200_1", "요청이 성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "SUCCESS201_1", "리소스가 성공적으로 생성되었습니다."),
    UPDATED(HttpStatus.OK, "SUCCESS200_2", "리소스가 성공적으로 수정되었습니다."),
    DELETED(HttpStatus.OK, "SUCCESS200_3", "리소스가 성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}