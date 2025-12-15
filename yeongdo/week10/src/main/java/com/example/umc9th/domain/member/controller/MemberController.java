package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.code.MemberSuccessCode;
import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    // 회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDto.JoinDto> signup(
            // 검증 실패 시 -> MethodArgumentNotValidException 발생
            @RequestBody @Valid MemberReqDto.JoinDto dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.Found, memberCommandService.signUp(dto));
    }

    // 로그인
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDto.LoginDto> login(
            @RequestBody @Valid MemberReqDto.LoginDto dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.Found, memberQueryService.login(dto));
    }

}
