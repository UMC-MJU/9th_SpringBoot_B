package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

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


    public static MissionResDto.MemberMissionDto toMemberMissionDto(MissionAssignment ma) {
        return MissionResDto.MemberMissionDto.builder()
                .missionTitle(ma.getMission().getTitle())
                .description(ma.getMission().getDescription())
                .baseReward(ma.getMission().getBaseReward())
                .missionStatus(ma.getStatus())
                .build();
    }

    public static MissionResDto.MemberMissionListDto toMemberMissionListDto(Page<MissionAssignment> result) {
        return MissionResDto.MemberMissionListDto.builder()
                .memberMissionList(result.getContent().stream().map(MissionAssignmentConverter::toMemberMissionDto).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

}
