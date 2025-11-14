package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.dto.MemberInfoDto;
import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이름, 이메일, 전화번호, 포인트 조회
    @Query("SELECT new com.example.umc9th.domain.member.dto.MemberInfoDto(" +
            "m.nickname, m.email, m.phone, m.point) " +
            "FROM Member m WHERE m.id = :memberId")
    Optional<MemberInfoDto> findMemberInfoById(@Param("memberId") Long memberId);
}
