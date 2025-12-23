package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    ReviewResDto.MyReviewPreviewListDto findMyReviews(Long memberId, Integer page);

    ReviewResDto.ReviewPreviewListDto findStoreReview(String storeName, Integer page);
}
