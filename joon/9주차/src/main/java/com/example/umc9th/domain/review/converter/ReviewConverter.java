package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewConverter {
    // Dto -> Entity
    public static Review toReview(Store store, Member member, ReviewReqDto.CreateDto dto) {
        Review review = Review.builder()
                .comment(dto.comment())
                .rating(dto.rating())
                .store(store)
                .member(member)
                .build();

        List<String> photoUrls = dto.photoUrls();

        if (photoUrls !=null && !photoUrls.isEmpty()) {
            List<ReviewPhoto> photos = new ArrayList<>();

            for (String url : photoUrls) {
                ReviewPhoto photo = ReviewPhoto.builder()
                        .photoUrl(url)
                        .review(review)
                        .build();
                photos.add(photo);
            }
            review.getReviewPhotoList().addAll(photos);
        }

        return review;
    }

    // Entity -> DTO
    public static ReviewResDto.ReviewCreateDto toReviewResDto(Review review) {
        return ReviewResDto.ReviewCreateDto.from(review);
    }

    // result -> Dto
    public static ReviewResDto.ReviewPreViewListDto toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDto.ReviewPreViewListDto.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDto)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDto.ReviewPreViewDto toReviewPreviewDto(Review review) {
        return ReviewResDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getComment())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
