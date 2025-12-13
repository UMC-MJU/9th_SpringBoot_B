package com.example.umc9th.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MemberAddressDto(
        @NotBlank @Size(max = 20) String postalCode,
        @NotBlank @Size(max = 20) String addr1,
        String addr2
) {
}
