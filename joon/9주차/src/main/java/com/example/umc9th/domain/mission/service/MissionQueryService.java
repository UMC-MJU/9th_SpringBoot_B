package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.res.MissionListDto;
import com.example.umc9th.domain.mission.dto.res.MissionProgressDto;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;

    // 해당 지역의 완료한 미션 달성 갯수
    public MissionResDto.MissionProgressDto getCompletedMissionCount(Long memberId, Long regionId) {
        // 지역 존재 여부 확인
        boolean regionExists = regionRepository.existsById(regionId);
        if (!regionExists) {
            throw new MissionException(MissionErrorCode.REGION_NOT_FOUND);
        }

        Long count = missionRepository.getCompletedMissionCount(memberId, regionId);

        // 결과가 null일 경우, 완료한 미션이 0개라는 의미이므로 DTO를 직접 생성하여 반환
        if (count == null) {
            count = 0L;
        }

        return MissionResDto.MissionProgressDto.builder()
                .memberId(memberId)
                .regionId(regionId)
                .completedMissions(count)
                .build();
    }

    // 도전 가능한 미션 목록
    public Page<MissionResDto.MissionListDto> getAvailableMissions(Long memberId, Long regionId, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<Mission> missionPage = missionRepository.findAvailableMissions(memberId, regionId, pageRequest);

        if (missionPage.isEmpty()) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        return missionPage.map(MissionConverter::toMissionListDto);
    }
}
