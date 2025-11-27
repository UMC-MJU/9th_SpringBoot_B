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

    // 리뷰 생성용 DTO -> Review 엔티티 변환
    public static Review toReview(Store store, Member member, ReviewReqDto.CreateDto dto) {
        // 기본 리뷰 정보 세팅
        Review review = Review.builder()
                .comment(dto.comment())
                .rating(dto.rating())
                .store(store)
                .member(member)
                .build();

        // 사진 URL 리스트 추출
        List<String> photoUrls = dto.photoUrls();

        // 사진이 존재하는 경우에만 ReviewPhoto 엔티티 생성
        if (photoUrls != null && !photoUrls.isEmpty()) {
            List<ReviewPhoto> photos = new ArrayList<>();

            for (String url : photoUrls) {
                ReviewPhoto photo = ReviewPhoto.builder()
                        .photoUrl(url)
                        .review(review)
                        .build();
                photos.add(photo);
            }

            // 리뷰에 사진 리스트 연관관계 설정
            review.getReviewPhotoList().addAll(photos);
        }

        return review;
    }

    // Review 엔티티 -> 리뷰 생성 응답 DTO 변환
    public static ReviewResDto.ReviewCreateDto toReviewResDto(Review review) {
        return ReviewResDto.ReviewCreateDto.from(review);
    }

    // 가게 리뷰 목록 페이지 -> 가게 리뷰 목록 응답 DTO 변환
    public static ReviewResDto.ReviewPreViewListDto toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResDto.ReviewPreViewListDto.builder()
                // 페이지 안의 Review 엔티티들을 PreViewDto 리스트로 변환
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDto)
                        .toList()
                )
                // 페이징 메타데이터 설정
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Review 엔티티 -> 가게 리뷰 단건 미리보기 DTO 변환
    public static ReviewResDto.ReviewPreViewDto toReviewPreviewDto(Review review) {
        return ReviewResDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getComment())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    // 내가 쓴 리뷰 페이지 -> 내가 작성한 리뷰 목록 응답 DTO 변환
    public static ReviewResDto.MyReviewPageDto toMyReviewPageDto(Page<Review> page) {
        return ReviewResDto.MyReviewPageDto.builder()
                // Review 엔티티들을 MyReviewDto 리스트로 변환
                .reviewList(
                        page.getContent().stream()
                                .map(ReviewResDto.MyReviewDto::from)
                                .toList()
                )
                // 페이징 메타데이터 설정
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}