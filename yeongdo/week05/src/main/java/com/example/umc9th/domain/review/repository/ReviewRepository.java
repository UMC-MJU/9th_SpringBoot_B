package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Spring Data JPA의 파생 쿼리는 연관 엔티티의 속성까지 경로 탐색을 지원함
    boolean existsByMissionAssignmentId(Long assignmentId);

    // MissionAssignment필드 내부로 들어가서 Member로 들어가서 Id 기준으로 찾기
    @EntityGraph(attributePaths = "reviewReply")
    Page<Review> findByMissionAssignment_Member_IdOrderByCreatedAtDesc(Long memberId, Pageable pageable );
}
