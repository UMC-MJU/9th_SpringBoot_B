package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;

import java.time.LocalDateTime;

public record ReviewSummaryDto (
        String title,
        int rating,
        LocalDateTime createdAt
){
    public static ReviewSummaryDto from(Review review){
        return new ReviewSummaryDto(
                review.getTitle(),
                review.getRating(),
                review.getCreatedAt()
        );
    }
}
