package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;


public class MissionConverter {
    public static MissionResDto.StoreMissionDto toStoreMissionDto(Mission mission) {
        return MissionResDto.StoreMissionDto.builder()
                .missionType(mission.getType())
                .storeName(mission.getStore().getName())
                .description(mission.getDescription())
                .missionTitle(mission.getTitle())
                .baseReward(mission.getBaseReward())
                .build();
    }

    public static MissionResDto.StoreMissionListDto toStoreMissionListDto(Page<Mission> result) {
        return MissionResDto.StoreMissionListDto.builder()
                .storeMissionList(result.getContent().stream().map(MissionConverter::toStoreMissionDto).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
}
