package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    Page<MissionResDto.MyMissionPreviewDto> findMyMission(Long memberId, MissionStatus status, Pageable pageable);

    MissionResDto.StoreMissionListDto findStoreMission(String storeName, Integer page);

    MissionResDto.MemberMissionListDto findMemberMission(
            Long memberId,
            Integer page,
            MissionStatus status
    );
}
