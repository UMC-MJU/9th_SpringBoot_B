package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.req.MissionReqDto;
import com.example.umc9th.domain.mission.dto.res.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MissionConverter {
    // Dto -> Entity (미션 생성용)
    public static Mission toMission(Store store, MissionReqDto.CreateDto dto) {
        LocalDateTime deadline = LocalDateTime.parse(dto.deadline(), DEADLINE_FORMATTER);

        return Mission.builder()
                .missionName(dto.missionName())
                .rewardPoint(dto.rewardPoint() != null ? dto.rewardPoint() : 0)
                .deadline(deadline)
                .store(store)
                .build();
    }

    // Entity -> DTO (미션 생성 응답용)
    public static MissionResDto.CreateDto toMissionResDto(Mission mission) {
        return MissionResDto.CreateDto.builder()
                .id(mission.getId())
                .storeId(mission.getStore().getId())
                .missionName(mission.getMissionName())
                .rewardPoint(mission.getRewardPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    // Entity -> DTO (가게/지역 미션 목록에서 한 건)
    public static MissionResDto.MissionListDto toMissionListDto(Mission mission) {
        int daysLeft = (int) ChronoUnit.DAYS.between(
                LocalDate.now(),
                mission.getDeadline().toLocalDate()
        );

        return MissionResDto.MissionListDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getStoreName())
                .missionName(mission.getMissionName())
                .rewardPoint(mission.getRewardPoint())
                .daysLeft(daysLeft)
                .build();
    }
}
