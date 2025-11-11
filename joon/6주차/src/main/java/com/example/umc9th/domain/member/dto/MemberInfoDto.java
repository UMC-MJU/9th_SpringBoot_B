package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {
    private String nickname;
    private String email;
    private String phone;
    private Integer point;
}
