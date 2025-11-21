package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    REVIEW_FORBIDDEN_NO_COMPLETED_MISSION(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 가게에서 완료한 미션이 없어 리뷰를 작성할 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

