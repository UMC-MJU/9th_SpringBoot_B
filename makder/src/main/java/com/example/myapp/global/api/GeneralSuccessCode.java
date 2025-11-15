package com.example.myapp.global.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 성공 코드 정의
    COMMON200("200", "성공입니다.");

    private final String code;
    private final String message;
}
