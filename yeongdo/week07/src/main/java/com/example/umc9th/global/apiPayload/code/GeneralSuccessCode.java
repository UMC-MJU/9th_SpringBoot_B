package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(
            HttpStatus.OK,
            "COMMOM200",
            "성공적으로 요청을 처리했습니다."
    ),
    CREATED(
            HttpStatus.CREATED,
            "SUCCESS201",
            "리소스가 성공적으로 생성되었습니다."
    ),
    ACCEPTED(
            HttpStatus.ACCEPTED,
            "SUCCESS202",
            "비동기 접수가 성공적을 완료되었습니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
