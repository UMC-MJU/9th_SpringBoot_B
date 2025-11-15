package com.example.myapp.global.exception.code;

import com.example.myapp.global.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR("COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST("COMMON400", "잘못된 요청입니다.");

    private final String code;
    private final String message;
}
