package com.example.myapp.domain.review.service;

import com.example.myapp.domain.review.entity.Review;
import com.example.myapp.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * 내가 작성한 리뷰 목록 조회 (동적 쿼리)
     */
    public Page<Review> getMyReviews(Long userId, Long storeId, Integer star, Pageable pageable) {
        // Repository에 구현한 동적 쿼리 메서드 호출
        return reviewRepository.findMyReviews(userId, storeId, star, pageable);
    }
}
