package com.example.umc9th.domain.review.Service;

import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.dto.ReviewSummaryDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public Page<ReviewSummaryDto> findMyReviews(Long memberId, String storeName, Float rating, Pageable pageable) {
        ReviewCond cond = new ReviewCond(memberId, storeName, rating);
        return reviewRepository.findMyReviews(cond, pageable);
    }

}
