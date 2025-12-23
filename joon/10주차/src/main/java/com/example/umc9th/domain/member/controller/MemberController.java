package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.req.MemberReqDto;
import com.example.umc9th.domain.member.dto.res.MemberInfoDto;
import com.example.umc9th.domain.member.dto.res.MemberResDto;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.MemberCommandService;
import com.example.umc9th.domain.member.service.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @GetMapping("/info")
    public ApiResponse<MemberInfoDto> getMemberInfo(@RequestParam(name = "memberId") Long memberId) {
        MemberInfoDto memberInfoDto = this.memberQueryService.getMemberInfo(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberInfoDto);
    }

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDto.JoinDto> registerMember(@RequestBody @Valid MemberReqDto.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.registerMember(dto));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDto.LoginDto> login(@RequestBody @Valid MemberReqDto.LoginDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}
