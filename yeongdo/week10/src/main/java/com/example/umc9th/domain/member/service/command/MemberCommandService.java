package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import com.example.umc9th.global.auth.enums.Role;

public interface MemberCommandService {
    MemberResDto.JoinDto signUp(
            MemberReqDto.JoinDto dto);
}
