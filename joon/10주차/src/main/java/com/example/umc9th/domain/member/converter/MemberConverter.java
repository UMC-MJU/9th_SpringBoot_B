package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDto;
import com.example.umc9th.domain.member.dto.res.MemberResDto;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {
    // Entity -> DTO
    public static MemberResDto.JoinDto toJoinDto(Member member) {
        return MemberResDto.JoinDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(MemberReqDto.JoinDTO dto) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .nickname(dto.nickname())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .gender(dto.gender())
                .build();
    }
}
