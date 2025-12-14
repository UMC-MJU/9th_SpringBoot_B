package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.code.FoodErrorCode;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    @Override
    public MemberResDto.JoinDto signUp(
            MemberReqDto.JoinDto dto
    ) {
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);

        if(dto.preferCategory().size() > 1) {
            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map( id-> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow( () -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .toList();
            //MemberFood가 주인이니까 memberFoodRepository.saveAll()만 해도 DB는 문제 X
            memberFoodRepository.saveAll(memberFoodList);
        }
        // DB 적용
        memberRepository.save(member);

        return MemberConverter.toJoinDto(member);
    }
}
