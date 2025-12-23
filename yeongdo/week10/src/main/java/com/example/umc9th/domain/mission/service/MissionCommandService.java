package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.enums.MissionType;

public interface MissionCommandService {
    MissionResDto.StartDto startMission(Long memberId, Long missionId, MissionType type);
}
