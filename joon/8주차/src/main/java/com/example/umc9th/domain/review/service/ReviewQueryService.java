package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public List<ReviewResDto> searchReview(Long memberId, String storeName, Integer rating) {
        BooleanBuilder builder = new BooleanBuilder();
        QReview review = QReview.review;

        // 내가 작성한 리뷰
        builder.and(review.member.id.eq(memberId));

        // 가게명 필터
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.storeName.containsIgnoreCase(storeName));
        }

        // 별점 필터
        if (rating != null) {
            int lower = rating;
            int upper = rating + 1;
            builder.and(review.rating.goe(lower).and(review.rating.lt(upper)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 검색 결과가 비어있으면 예외 발생
        if (reviewList.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        return reviewList.stream()
                .map(ReviewResDto::from)
                .collect(Collectors.toList());
    }
}
