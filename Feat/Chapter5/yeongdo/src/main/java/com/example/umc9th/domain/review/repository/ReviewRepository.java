package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Spring Data JPA의 파생 쿼리는 연관 엔티티의 속성까지 경로 탐색을 지원함
    boolean existsByMissionAssignmentId(Long assignmentId);
}
