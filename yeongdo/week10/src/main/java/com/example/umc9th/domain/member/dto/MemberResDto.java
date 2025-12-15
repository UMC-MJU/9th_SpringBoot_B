package com.example.umc9th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDto {

    // 회원가입
    @Builder
    public record JoinDto(
            Long memberId,
            LocalDateTime createdAt
    ){}

    // 로그인
    @Builder
    public record LoginDto(
            Long memberId,
            String accessToken
    ){}
}
