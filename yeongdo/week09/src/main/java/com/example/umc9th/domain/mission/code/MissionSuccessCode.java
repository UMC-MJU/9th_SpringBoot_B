package com.example.umc9th.domain.mission.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_ASSIGNMENT_CREATED(
            HttpStatus.OK,
            "MISSION201_1",
            "성공적으로 미션을 시작했습니다."
    ),
    STORE_MISSION_LIST_FOUND_BY_STORE(HttpStatus.OK,
            "MISSION200_1",
            "가게별 미션 조회에 성공했습니다.")
    ,

    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
