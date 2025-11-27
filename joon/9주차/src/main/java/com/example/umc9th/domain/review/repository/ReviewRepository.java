package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // 특정 가게에 대한 리뷰 목록을 페이징 조회
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    // 특정 회원이 작성한 리뷰 목록을 페이징 조회
    Page<Review> findAllByMemberId(Long memberId, PageRequest pageRequest);
}
