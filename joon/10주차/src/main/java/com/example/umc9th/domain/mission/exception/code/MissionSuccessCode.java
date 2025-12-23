package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 성공적으로 등록되었습니다."),
    FOUND(HttpStatus.OK, "MISSION200_1", "성공적으로 미션을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
