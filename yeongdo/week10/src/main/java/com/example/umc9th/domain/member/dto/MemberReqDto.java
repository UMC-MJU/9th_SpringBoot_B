package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDto {

    // 회원가입
    public record JoinDto(
            @NotBlank
            String name,
            @Email
            String email, // 이메일
            @NotBlank
            String password,
            @NotBlank
            String phone,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            MemberAddressDto address,
            @NotNull @ExistFoods
            List<Long> preferCategory
    ){}

    // 로그인
    public record LoginDto(
            @NotBlank
            String email,

            @NotBlank
            String password
    ){}
}
