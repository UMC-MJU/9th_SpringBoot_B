package com.example.umc9th.domain.mypage.service;

import com.example.umc9th.domain.mypage.dto.MyPageDto;

public interface MyPageService {
    public MyPageDto getMyPage(Long memberId);
}
