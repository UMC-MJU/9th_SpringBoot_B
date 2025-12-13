package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 사용자가 진행중/완료한 미션 목록 조회 (page = 1 기반, 한 페이지 10개)
    public MemberMissionResDto.MemberMissionPageDto getMyMissions(Long memberId, Integer page) {
        // 사용자 존재 여부 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        int pageIndex = (page == null ? 1 : page) - 1;  // page는 1부터 받고, 내부에선 0-based로 변환 (page - 1)
        PageRequest pageRequest = PageRequest.of(pageIndex, 10);

        Page<MemberMission> result = memberMissionRepository.findByMember_Id(memberId, pageRequest);

        if (result.isEmpty()) {
            throw new MemberMissionException(MemberMissionErrorCode.MISSION_NOT_FOUND);
        }

        return MemberMissionConverter.toMemberMissionPageDto(result);
    }
}
