package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.res.MemberInfoDto;
import com.example.umc9th.domain.member.service.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberQueryService memberQueryService;

    @GetMapping("/info")
    public ApiResponse<MemberInfoDto> getMemberInfo(@RequestParam(name = "memberId") Long memberId) {
        MemberInfoDto memberInfoDto = this.memberQueryService.getMemberInfo(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberInfoDto);
    }
}
