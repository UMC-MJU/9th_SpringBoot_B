package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;

public class MemberMissionReqDto {
    public record CreateDto(
            @NotNull(message = "도전하는 회원 ID를 입력해 주세요.")
            Long memberId,

            @NotNull(message = "도전할 미션 ID를 입력해 주세요.")
            Long missionId
    ) {}
}
