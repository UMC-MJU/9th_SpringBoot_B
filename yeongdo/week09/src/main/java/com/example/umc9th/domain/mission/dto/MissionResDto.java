package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionType;
import lombok.Builder;

import java.util.List;

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

    @Builder
    public record StoreMissionDto(
            MissionType missionType,
            String storeName,
            String missionTitle,
            String description,
            int baseReward
    ){}

    @Builder
    public record StoreMissionListDto(
            List<StoreMissionDto> storeMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyMissionPreviewDto (
        Long assignmentId,
        String storeName,
        String missionTitle,
        int baseReward
    ) {
        public static MyMissionPreviewDto of (MissionAssignment ma) {
            return MyMissionPreviewDto.builder()
                    .assignmentId(ma.getId())
                    .storeName(ma.getMission().getStore().getName())
                    .missionTitle(ma.getMission().getTitle())
                    .baseReward(ma.getMission().getBaseReward())
                    .build();
        }
    }

}
