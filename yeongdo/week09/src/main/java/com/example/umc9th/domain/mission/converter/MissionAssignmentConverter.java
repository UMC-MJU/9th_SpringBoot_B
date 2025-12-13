package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;

public class MissionAssignmentConverter {

    public static MissionResDto.StartDto toStartDto(MissionAssignment ma) {
        return MissionResDto.StartDto.builder()
                .missionTitle(ma.getMission().getTitle())
                .storeName(ma.getMission().getStore().getName())
                .baseReward(ma.getMission().getBaseReward())
                .build();
    }

    public static MissionAssignment toEntity(Member member, Mission mission) {
        return MissionAssignment.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();
    }

}
