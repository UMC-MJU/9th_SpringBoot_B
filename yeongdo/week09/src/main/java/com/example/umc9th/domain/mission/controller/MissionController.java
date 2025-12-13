package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.dto.MissionReqDto;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.enums.MissionType;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.domain.review.code.ReviewSuccessCode;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;


    // 미션 도전하기
    @Override
    @PostMapping("members/{memberId}/missions/{missionId}")
    public ApiResponse<MissionResDto.StartDto> startMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId,
            @RequestBody(required = false) MissionReqDto.StartDto dto
    ) {
        MissionType type = (dto != null && dto.missionType() != null)
                ? dto.missionType()
                : MissionType.VISIT;
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_ASSIGNMENT_CREATED, missionCommandService.startMission(memberId, missionId, type));
    }

    // 가게별 미션 조회
    @Override
    @GetMapping("/stores/missions")
    public ApiResponse<MissionResDto.StoreMissionListDto> getMissions(
            @RequestParam String storeName,
            @RequestParam @CheckPage Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.STORE_MISSION_LIST_FOUND_BY_STORE;
        return ApiResponse.onSuccess(code, missionQueryService.findStoreMission(storeName, page));
    }

    // 내가 진행중인 미션 목록


}