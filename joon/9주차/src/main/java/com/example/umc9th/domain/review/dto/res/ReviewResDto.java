package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.review.entity.Review;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewDto {
        private String storeName;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;

        public static MyReviewDto from(Review review) {
            if (review == null) {
                return null;
            }

            return MyReviewDto.builder()
                    .storeName(review.getStore().getStoreName())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .createdAt(review.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCreateDto {
        private String storeName;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;
        private ReplyResDto reply;

        public static ReviewCreateDto from(Review review) {
            if (review == null) {
                return null;
            }

            return ReviewCreateDto.builder()
                    .storeName(review.getStore().getStoreName())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .createdAt(review.getCreatedAt())
                    .reply(ReplyResDto.from(review.getReply()))
                    .build();
        }
    }

    @Builder
    public record ReviewPreViewListDto(
            List<ReviewPreViewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record ReviewPreViewDto(
            String ownerNickname,
            Integer score,
            String body,
            LocalDate createdAt
    ){}
}
