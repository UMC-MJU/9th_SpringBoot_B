package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {
    CHALLENGED(HttpStatus.CREATED, "MEMBER_MISSION201_1", "성공적으로 미션을 등록했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
