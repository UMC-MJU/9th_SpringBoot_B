package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionDto {
    private Long assignmentId;
    private String storeName;
    private String missionTitle;
    private int baseReward;

    public static MissionDto of(MissionAssignment assignment) {
        return MissionDto.builder()
                .assignmentId(assignment.getId())
                .storeName(assignment.getMission().getStore().getName())
                .missionTitle(assignment.getMission().getTitle())
                .baseReward(assignment.getMission().getBaseReward())
                .build();
    }
}
