package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionListDto;
import com.example.umc9th.domain.mission.dto.res.MissionProgressDto;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {
    private final MissionQueryService missionQueryService;

    // 특정 지역에서 완료한 미션 개수 조회
    @GetMapping("/completed-count")
    public ApiResponse<MissionProgressDto> getCompletedMissionCount(
            @RequestParam("memberId") Long memberId,
            @RequestParam("regionId") Long regionId) {

        MissionProgressDto responseDto = missionQueryService.getCompletedMissionCount(memberId, regionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDto);
    }

    // 도전 가능 미션 목록 조회
    @GetMapping("/available")
    public ApiResponse<Page<MissionListDto>> getAvailableMissions(
            @RequestParam("memberId") Long memberId,
            @RequestParam("regionId") Long regionId,
            @RequestParam(defaultValue = "0") int page) {

        Page<MissionListDto> missionPage = missionQueryService.getAvailableMissions(memberId, regionId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionPage);
    }
}
