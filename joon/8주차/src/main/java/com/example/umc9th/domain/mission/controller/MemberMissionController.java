package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDto;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.MemberMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {
    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    @GetMapping("/list")
    public ApiResponse<Page<MemberMissionResDto>> getMyMissions(
            @RequestParam Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MemberMissionResDto> responseDtoPage = memberMissionQueryService.getMyMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDtoPage);
    }

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResDto> challengeMission(@RequestBody @Valid MemberMissionReqDto.CreateDto dto) {
        MemberMissionResDto response = memberMissionCommandService.challengeMission(dto.memberId(), dto.missionId());
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }
}
