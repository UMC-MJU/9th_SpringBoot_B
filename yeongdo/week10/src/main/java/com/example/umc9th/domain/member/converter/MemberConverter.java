package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.auth.enums.Role;

public class MemberConverter {

    public static MemberResDto.JoinDto toJoinDto(Member member) {
        return MemberResDto.JoinDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO, Salted Password, Role -> Entity
    public static Member toMember(
            MemberReqDto.JoinDto dto,
            String password,
            Role role
    ) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .phoneNumber(dto.phone())
                .gender(dto.gender())
                .birth(dto.birth())
                .build();
    }
}
