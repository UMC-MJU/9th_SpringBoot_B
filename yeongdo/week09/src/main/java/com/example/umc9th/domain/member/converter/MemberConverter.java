package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDto.JoinDto toJoinDto(Member member) {
        return MemberResDto.JoinDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(
            MemberReqDto.JoinDto dto
    ) {
        return Member.builder()
                .name(dto.name())
                .address(MemberAddressConverter.toEntity(dto.address()))
                .email(dto.email())
                .phoneNumber(dto.phone())
                .gender(dto.gender())
                .birth(dto.birth())
                .build();
    }
}
