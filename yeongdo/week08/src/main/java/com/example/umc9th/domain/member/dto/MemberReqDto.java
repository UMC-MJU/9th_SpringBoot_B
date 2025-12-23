package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDto {
    public record JoinDto(
            @NotBlank
            String name,
            @NotNull
            MemberAddressDto address,
            @NotBlank
            String email,
            @NotBlank
            String phone,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
