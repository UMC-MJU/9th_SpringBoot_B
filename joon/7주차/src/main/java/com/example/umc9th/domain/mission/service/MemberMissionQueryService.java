package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.res.MemberMissionDto;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMissionDto> getMyMissions(Long memberId, Pageable pageable) {
        // 사용자 존재하는지 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        return memberMissionRepository.findMyMissionList(memberId, pageable);
    }
}
