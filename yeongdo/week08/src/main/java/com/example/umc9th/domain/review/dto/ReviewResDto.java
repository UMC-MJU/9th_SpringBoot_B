package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDto {

    @Builder
    public record SearchDto(
            String title,
            String content,
            float rating,
            LocalDateTime createdAt
    ){}

    @Builder
    public record CreateReview(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
