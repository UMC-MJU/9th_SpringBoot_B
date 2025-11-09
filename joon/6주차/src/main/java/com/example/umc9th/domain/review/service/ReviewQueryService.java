package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

        return reviewList.stream()
                .map(ReviewResDto::from)
                .toList();
    }
}
