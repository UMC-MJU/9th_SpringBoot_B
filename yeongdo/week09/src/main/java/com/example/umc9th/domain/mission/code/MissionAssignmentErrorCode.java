package com.example.umc9th.domain.mission.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionAssignmentErrorCode implements BaseErrorCode {
    NOT_COMPLETED(HttpStatus.CONFLICT,
            "MISSION_ASSIGNMENT_400_1",
    "완료한 미션만 리뷰를 작성할 수 있습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT,
            "MISSION_ASSIGNMENT_400_2",
            "이미 작성한 미션은 리뷰를 다시 작성하실 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
