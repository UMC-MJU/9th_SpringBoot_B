package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MissionReqDto {
    public record CreateDto(
            @NotBlank(message = "미션 이름을 입력해주세요.")
            String missionName,

            @NotNull(message = "보상 포인트를 입력해주세요.")
            Integer rewardPoint,

            @NotBlank(message = "마감일을 입력해주세요. (yyyy-MM-dd'T'HH:mm:ss)")
            String deadline
    ) {}
}
