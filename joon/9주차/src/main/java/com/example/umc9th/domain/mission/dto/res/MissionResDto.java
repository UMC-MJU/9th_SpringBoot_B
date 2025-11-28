package com.example.umc9th.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDto {
    @Builder
    public record CreateDto(
            Long id,
            Long storeId,
            String missionName,
            int rewardPoint,
            LocalDateTime deadline
    ) {}

    // 미션 목록의 한 건
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDto {
        private Long missionId;
        private String storeName;
        private String missionName;
        private int rewardPoint;
        private int daysLeft;
    }

    // 완료 미션 개수 조회용 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionProgressDto {
        private Long memberId;
        private Long regionId;
        private Long completedMissions;
    }
}
