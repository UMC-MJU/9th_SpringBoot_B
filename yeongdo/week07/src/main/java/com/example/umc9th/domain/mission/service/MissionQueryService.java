package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionDto;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    Page<MissionDto> findMyMission(Long memberId, MissionStatus status, Pageable pageable);
}
