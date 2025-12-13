package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.code.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.code.MissionErrorCode;
import com.example.umc9th.domain.mission.converter.MissionAssignmentConverter;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.enums.MissionType;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.repository.MissionAssignmentRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionAssignmentRepository missionAssignmentRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public MissionResDto.StartDto startMission(Long memberId, Long missionId, MissionType type) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow( () -> new  MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findByIdAndType(missionId, type)
                .orElseThrow( () -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 종료된 미션일 때
        if(mission.getEndsAt() != null && mission.getEndsAt().isBefore(LocalDateTime.now())){
            throw new MissionException(MissionErrorCode.MISSION_CLOSED);
        }

        // 이미 진행 중인 미션일 때
        if(missionAssignmentRepository.existsByMember_IdAndMission_IdAndStatus(memberId, missionId, MissionStatus.IN_PROGRESS)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_ASSIGNED);
        }

        if(missionAssignmentRepository.existsByMember_IdAndMission_IdAndStatus(memberId, missionId, MissionStatus.COMPLETED)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_ASSIGNED);
        }

        MissionAssignment ma = missionAssignmentRepository.save(MissionAssignmentConverter.toEntity(member, mission));
        return MissionAssignmentConverter.toStartDto(ma);

    }
}
