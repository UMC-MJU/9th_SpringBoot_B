package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionDto;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.MissionAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {
    private final MissionAssignmentRepository assignmentRepo;

    public Page<MissionDto> findMyMission(
            Long memberId, MissionStatus status, Pageable pageable
    ) {
        return assignmentRepo.findByMember_IdAndStatusOrderByStartedAtDesc(memberId, status, pageable)
                .map(MissionDto::of);
    }
}
