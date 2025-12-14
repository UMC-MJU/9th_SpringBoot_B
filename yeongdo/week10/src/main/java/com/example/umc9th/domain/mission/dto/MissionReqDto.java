package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionType;

public class MissionReqDto {

    public record StartDto(
            MissionType missionType
    ){}
}
