package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.review.entity.Review;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    // 내가 쓴 리뷰 한 건에 대한 정보 DTO
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

    // 리뷰 생성 후 반환되는 응답 DTO
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

    // 가게의 리뷰 목록 + 페이징 정보를 담는 DTO
    @Builder
    public record ReviewPreViewListDto(
            List<ReviewPreViewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 가게 리뷰 한 건을 간략하게 보여줄 때 사용하는 DTO
    @Builder
    public record ReviewPreViewDto(
            String ownerNickname,
            Integer score,
            String body,
            LocalDate createdAt
    ){}

    // 내가 작성한 리뷰 목록 + 페이징 정보를 담는 DTO
    @Builder
    public record MyReviewPageDto(
            List<MyReviewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
