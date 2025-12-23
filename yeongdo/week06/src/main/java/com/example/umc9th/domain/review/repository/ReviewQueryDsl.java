package com.example.umc9th.domain.review.repository;


import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.dto.ReviewSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {
    Page<ReviewSummaryDto> findMyReviews(ReviewCond cond, Pageable pageable);
}
