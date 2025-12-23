package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.projection.MemberContactView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
   // 인터페이스 프로젝션을 통해 name / email / phoneNumber만 한번에 가져옴
    Optional<MemberContactView> findMemberContactViewById(Long id);

    Optional<Member> findByEmail(String email);
}
