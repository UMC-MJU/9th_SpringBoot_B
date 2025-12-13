package com.example.umc9th.domain.mission.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION_404_1",
            "미션을 찾을 수 없습니다."
    ),
    MISSION_ALREADY_ASSIGNED(
            HttpStatus.CONFLICT,
            "MISSION_400_1",
            "이미 진행 중인 미션입니다."
    ),
    MISSION_ALREADY_COMPLETED(
            HttpStatus.CONFLICT,
            "MISSION_400_2",
            "이미 참여했던 미션입니다."
    )
    ,
    MISSION_CLOSED(
            HttpStatus.CONFLICT,
            "MISSION_400_3",
            "이미 종료된 미션입니다."
    ),
    STORE_MISSION_LIST_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "가게에 해당하는 미션이 없습니다."
    )
    ;


    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
