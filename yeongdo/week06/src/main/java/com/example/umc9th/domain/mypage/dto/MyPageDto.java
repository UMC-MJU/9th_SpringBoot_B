package com.example.umc9th.domain.mypage.dto;

import com.example.umc9th.domain.member.entity.MemberAddress;

public record MyPageDto(
        String name,
        String email,
        String phoneNumber,
        long totalPoint

) {}
