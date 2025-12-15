package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.code.FoodErrorCode;
import com.example.umc9th.domain.member.converter.MemberAddressConverter;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDto;
import com.example.umc9th.domain.member.dto.MemberResDto;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.MemberAddress;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDto.JoinDto signUp(
            MemberReqDto.JoinDto dto
    ) {

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성 : 유저 / 관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        MemberAddress address = MemberAddressConverter.toEntity(dto.address());
        member.setAddress(address);

        dto.preferCategory().forEach(id -> {
            Food food = foodRepository.findById(id)
                    .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));
            member.addMemberFood(MemberFood.builder().food(food).build());
        });

        Member savedMember = memberRepository.save(member);
        return MemberConverter.toJoinDto(savedMember);
    }
}
