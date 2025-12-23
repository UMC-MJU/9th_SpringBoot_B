package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDto;
import com.example.umc9th.domain.member.dto.res.MemberResDto;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberFoodId;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.exception.code.FoodErrorCode;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public MemberResDto.JoinDto registerMember(MemberReqDto.JoinDTO dto) {
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1) {
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()) {
                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .id(new MemberFoodId(member.getId(), food.getId()))
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }

        // 응답 DTO 생성
        return MemberConverter.toJoinDto(member);
    }
}
