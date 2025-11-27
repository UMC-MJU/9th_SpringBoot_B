package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDto {
    @Builder
    public record CreateDto(
            Long id,
            Long storeId,
            String missionName,
            int rewardPoint,
            LocalDateTime deadline
    ) {}
}
