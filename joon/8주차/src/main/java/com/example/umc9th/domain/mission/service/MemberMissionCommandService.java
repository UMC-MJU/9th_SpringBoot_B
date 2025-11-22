package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMissionId;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionResDto challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        // 이미 도전 중인 미션인지 확인
        MemberMissionId id = new MemberMissionId(member.getId(), mission.getId());
        if (memberMissionRepository.existsById(id)) {
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_CHALLENGING_MISSION);
        }

        // 마감된 미션인지 확인
        if (mission.getDeadline().isBefore(LocalDateTime.now())) {
            throw new MemberMissionException(MemberMissionErrorCode.MISSION_DEADLINE_PASSED);
        }

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        MemberMission saved = memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toMemberMissionDto(saved);
    }
}
