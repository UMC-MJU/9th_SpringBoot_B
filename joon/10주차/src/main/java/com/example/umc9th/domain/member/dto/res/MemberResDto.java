package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDto {
    @Builder
    public record JoinDto(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
