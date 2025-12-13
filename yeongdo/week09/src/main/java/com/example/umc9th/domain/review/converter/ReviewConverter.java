package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.review.dto.ReviewReqDto;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

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

    public static ReviewResDto.ReviewPreviewDto toReviewPreviewDto(Review review) {
        return ReviewResDto.ReviewPreviewDto.builder()
                .ownerNickName(review.getMissionAssignment().getMember().getName())
                .rating(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
    public static ReviewResDto.ReviewPreviewListDto toReviewPreviewListDto(Page<Review> result) {
        return ReviewResDto.ReviewPreviewListDto.builder()
                .reviewList(result.getContent().stream().map(ReviewConverter::toReviewPreviewDto).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDto.MyReviewPreviewDto toMyReviewPreviewDto(Review review) {
        return ReviewResDto.MyReviewPreviewDto.builder()
                .memberId(review.getMissionAssignment().getMember().getId())
                .rating(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    public static ReviewResDto.MyReviewPreviewListDto toMyReviewPreviewListDto(Page<Review> result) {
        return ReviewResDto.MyReviewPreviewListDto.builder()
                .myReviewList(result.getContent().stream().map(ReviewConverter::toMyReviewPreviewDto).toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

}
