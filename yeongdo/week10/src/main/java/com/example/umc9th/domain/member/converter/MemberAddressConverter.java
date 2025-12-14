package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberAddressDto;
import com.example.umc9th.domain.member.entity.MemberAddress;

public class MemberAddressConverter {
    public static MemberAddressDto toDto(MemberAddress addr) {
        return MemberAddressDto.builder()
                .postalCode(addr.getPostalCode())
                .addr1(addr.getAddr1())
                .addr2(addr.getAddr2())
                .build();
    }

    public static MemberAddress toEntity(MemberAddressDto dto) {
        return MemberAddress.builder()
                .postalCode(dto.postalCode())
                .addr1(dto.addr1())
                .addr2(dto.addr2())
                .build();
    }
}
