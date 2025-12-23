package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResDto;

public interface ReviewCommandService {
    ReviewResDto.CreateReview writeReview(Long assignmentId, Long memberId, String title, String content, float rating);
}
