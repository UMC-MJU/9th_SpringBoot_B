package com.example.umc9th.domain.member.repository.projection;

// 인터페이스 프로젝션
// 조회 전용 뷰
public interface MemberContactView {
    String getName();
    String getEmail();
    String getPhoneNumber();
}
