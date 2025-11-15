package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionDto;
import com.example.umc9th.domain.mission.service.MemberMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {
    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/list")
    public ApiResponse<Page<MemberMissionDto>> getMyMissions(
            @RequestParam Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MemberMissionDto> responseDtoPage = memberMissionQueryService.getMyMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, responseDtoPage);
    }
}
