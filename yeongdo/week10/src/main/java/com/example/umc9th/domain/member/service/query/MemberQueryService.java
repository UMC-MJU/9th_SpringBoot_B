package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import jakarta.validation.Valid;


public interface MemberQueryService {
    MemberResDto.LoginDto login(MemberReqDto.LoginDto dto);

}
