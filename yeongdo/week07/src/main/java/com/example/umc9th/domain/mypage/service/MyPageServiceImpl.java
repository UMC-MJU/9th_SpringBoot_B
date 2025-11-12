package com.example.umc9th.domain.mypage.service;

import com.example.umc9th.domain.member.exception.MemberErrorCode;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.repository.projection.MemberContactView;
import com.example.umc9th.domain.mission.repository.PointLedgerRepository;
import com.example.umc9th.domain.mypage.dto.MyPageDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final MemberRepository memberRepository;
    private final PointLedgerRepository pointLedgerRepository;
    private final ReviewRepository reviewRepository;

    public MyPageDto getMyPage(Long memberId){
        // 프로필
        MemberContactView contact = memberRepository.findMemberContactViewById(memberId)
                .orElseThrow( () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 포인트 합계
        long totalPoint = pointLedgerRepository.sumDeltaByMemberId(memberId);

        return new MyPageDto(contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                totalPoint);
    }


}
