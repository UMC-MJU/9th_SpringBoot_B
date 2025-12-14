package com.example.umc9th.domain.store.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당하는 상점이 없습니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
