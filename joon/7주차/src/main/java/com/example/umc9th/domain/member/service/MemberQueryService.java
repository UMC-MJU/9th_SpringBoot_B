package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.res.MemberInfoDto;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberInfoDto getMemberInfo(Long memberId) {
        Optional<MemberInfoDto> memberInfoDto = this.memberRepository.findMemberInfoById(memberId);
        return memberInfoDto.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
