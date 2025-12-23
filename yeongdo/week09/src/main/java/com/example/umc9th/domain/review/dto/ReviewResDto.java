package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    @Builder
    public record SearchDto(
            String title,
            String content,
            float rating,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CreateReview(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewPreviewDto(
            String ownerNickName,
            Float rating,
            String body,
            LocalDate createdAt
    ){}

    @Builder
    public record ReviewPreviewListDto(
            List<ReviewPreviewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyReviewPreviewDto(
            Long memberId,
            Float rating,
            String body,
            LocalDate createdAt
    ){}

    @Builder
    public record MyReviewPreviewListDto(
            List<MyReviewPreviewDto> myReviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
