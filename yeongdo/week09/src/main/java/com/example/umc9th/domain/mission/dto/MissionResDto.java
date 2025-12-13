package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

public class MissionResDto {

    public record SearchDto(
            Long assignmentId,
            String storeName,
            String missionTitle,
            int baseReward
    ){}

    @Builder
    public record StartDto(
        String storeName,
        String missionTitle,
        int baseReward
    ){}
}
