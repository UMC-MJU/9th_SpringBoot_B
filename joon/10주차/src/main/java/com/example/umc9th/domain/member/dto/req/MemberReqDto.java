package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Address;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDto {
        public record JoinDTO(
                @NotBlank(message = "이름을 입력해주세요.")
                String name,

                @NotBlank(message = "이메일을 입력해주세요.")
                @Email(message = "올바른 이메일 형식이 아닙니다.")
                String email,

                @NotBlank(message = "비밀번호를 입력해주세요.")
                String password,

                @NotBlank(message = "닉네임을 입력해주세요.")
                String nickname,

                @NotNull(message = "성별을 선택해주세요.")
                Gender gender,

                @NotNull(message = "생년월일을 입력해주세요.")
                LocalDate birthDate,

                @NotNull(message = "주소를 입력해주세요.")
                Address address,

                @NotNull(message = "상세 주소를 입력해주세요.")
                String detailAddress,

                @ExistFoods
                List<Long> preferCategory
        ) {}

        // 로그인
        public record LoginDTO(
                @NotBlank(message = "이메일을 입력해주세요.")
                String email,
                @NotBlank(message = "비밀번호를 입력해주세요.")
                String password
        ) {}
}

