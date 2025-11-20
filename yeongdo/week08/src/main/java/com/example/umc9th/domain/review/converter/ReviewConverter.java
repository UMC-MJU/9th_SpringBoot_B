package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.review.dto.ReviewReqDto;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDto.SearchDto toSearchDto(Review review) {
        return ReviewResDto.SearchDto.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toEntity(ReviewReqDto.CreateReview dto, MissionAssignment ma) {
        return Review.builder()
                .missionAssignment(ma)
                .title(dto.title())
                .content(dto.content())
                .rating(dto.rating())
                .build();

    }

    public static ReviewResDto.CreateReview toCreateDto(Review review) {
        return ReviewResDto.CreateReview.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
