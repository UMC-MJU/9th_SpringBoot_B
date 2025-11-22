package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MemberMissionConverter {
    // Entity -> Dto
    public static MemberMissionResDto toMemberMissionDto(MemberMission memberMission) {
        return MemberMissionResDto.from(memberMission);
    }

    // Dto -> Entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.createMemberMission(member, mission);
    }
}
