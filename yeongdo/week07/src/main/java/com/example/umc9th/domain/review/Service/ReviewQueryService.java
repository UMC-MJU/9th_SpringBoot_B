package com.example.umc9th.domain.review.Service;

import com.example.umc9th.domain.review.dto.ReviewSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    Page<ReviewSummaryDto> findMyReviews(Long memberId, String storeName, Float rating, Pageable pageable);
}
