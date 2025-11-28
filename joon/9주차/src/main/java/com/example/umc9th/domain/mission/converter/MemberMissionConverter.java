package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {
    // Entity -> Dto
    public static MemberMissionResDto toMemberMissionDto(MemberMission memberMission) {
        return MemberMissionResDto.from(memberMission);
    }

    // Dto -> Entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.createMemberMission(member, mission);
    }

    // 내 미션 목록 + 페이징 DTO
    public static MemberMissionResDto.MemberMissionPageDto toMemberMissionPageDto(Page<MemberMission> page) {
        return MemberMissionResDto.MemberMissionPageDto.builder()
                .missionList(
                        page.getContent().stream()
                                .map(MemberMissionResDto::from)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
