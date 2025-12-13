package com.example.umc9th.domain.review.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    CREATED(
            HttpStatus.CREATED,
            "REVIEW201_1",
            "성공적으로 리뷰를 추가했습니다."
    ),
    OK(
            HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 조회했습니다."
    )
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
