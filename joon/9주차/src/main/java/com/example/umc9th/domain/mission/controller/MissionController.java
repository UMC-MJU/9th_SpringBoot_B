package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionReqDto;
import com.example.umc9th.domain.mission.dto.res.MissionListDto;
import com.example.umc9th.domain.mission.dto.res.MissionProgressDto;
import com.example.umc9th.domain.mission.dto.res.MissionResDto;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    // 특정 지역에서 완료한 미션 개수 조회
    @GetMapping("/missions/completed-count")
    public ApiResponse<MissionProgressDto> getCompletedMissionCount(
            @RequestParam("memberId") Long memberId,
            @RequestParam("regionId") Long regionId) {

        MissionProgressDto responseDto = missionQueryService.getCompletedMissionCount(memberId, regionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDto);
    }

    // 도전 가능 미션 목록 조회
    @GetMapping("/missions/available")
    public ApiResponse<Page<MissionListDto>> getAvailableMissions(
            @RequestParam("memberId") Long memberId,
            @RequestParam("regionId") Long regionId,
            @RequestParam(defaultValue = "0") int page) {

        Page<MissionListDto> missionPage = missionQueryService.getAvailableMissions(memberId, regionId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionPage);
    }

    // 가게에 미션 추가 API
    @PostMapping("/stores/{storeId}/missions/register")
    public ApiResponse<MissionResDto.CreateDto> createMission(@PathVariable Long storeId, @RequestBody @Valid MissionReqDto.CreateDto dto) {
        MissionResDto.CreateDto response = missionCommandService.createMission(storeId, dto);
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, response);
    }
}
