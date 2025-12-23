package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDto;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.MemberMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member-missions")
public class MemberMissionController implements MemberMissionControllerDocs {
    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    // 사용자가 진행중/완료한 미션 목록 조회 API
    @GetMapping("/list")
    public ApiResponse<MemberMissionResDto.MemberMissionPageDto> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        MemberMissionResDto.MemberMissionPageDto responseDto = memberMissionQueryService.getMyMissions(memberId, page);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.FOUND, responseDto);
    }

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResDto> challengeMission(@RequestBody @Valid MemberMissionReqDto.CreateDto dto) {
        MemberMissionResDto response = memberMissionCommandService.challengeMission(dto.memberId(), dto.missionId());
        return ApiResponse.onSuccess(MemberMissionSuccessCode.CHALLENGED, response);
    }
}
