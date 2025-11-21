package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.req.MissionReqDto;
import com.example.umc9th.domain.mission.dto.res.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MissionConverter {
    private static final DateTimeFormatter DEADLINE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Dto -> Entity
    public static Mission toMission(Store store, MissionReqDto.CreateDto dto) {
        LocalDateTime deadline = LocalDateTime.parse(dto.deadline(), DEADLINE_FORMATTER);

        return Mission.builder()
                .missionName(dto.missionName())
                .rewardPoint(dto.rewardPoint() != null ? dto.rewardPoint() : 0)
                .deadline(deadline)
                .store(store)
                .build();
    }

    // Entity -> DTO
    public static MissionResDto.CreateDto toMissionResDto(Mission mission) {
        return MissionResDto.CreateDto.builder()
                .id(mission.getId())
                .storeId(mission.getStore().getId())
                .missionName(mission.getMissionName())
                .rewardPoint(mission.getRewardPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
