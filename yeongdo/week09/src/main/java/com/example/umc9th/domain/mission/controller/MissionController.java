package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.dto.MissionReqDto;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.enums.MissionType;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;

    // 미션 도전하기
    @PostMapping("members/{memberId}/missions/{missionId}")
    public ApiResponse<MissionResDto.StartDto> startMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId,
            @RequestBody(required = false) MissionReqDto.StartDto dto
    ) {
        MissionType type = (dto != null && dto.missionType() != null)
                ? dto.missionType()
                : MissionType.VISIT;
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionCommandService.startMission(memberId, missionId, type));
    }
}