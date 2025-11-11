package com.example.myapp.domain.review.repository;

import com.example.myapp.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    /**
     * 내가 작성한 리뷰 목록을 동적 조건에 따라 조회
     *
     * @param userId    현재 로그인한 사용자 ID
     * @param storeId   필터링할 가게 ID (null 가능)
     * @param star      필터링할 별점 (null 가능)
     * @param pageable  페이지네이션 정보
     * @return 페이징 처리된 리뷰 목록
     */
    Page<Review> findMyReviews(Long userId, Long storeId, Integer star, Pageable pageable);
}
