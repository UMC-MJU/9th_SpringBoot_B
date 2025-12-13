package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // Spring Data JPA의 파생 쿼리는 연관 엔티티의 속성까지 경로 탐색을 지원함
    boolean existsByMissionAssignmentId(Long assignmentId);

    // 가게별 리뷰 목록 조회
    @EntityGraph(attributePaths ={
            "missionAssignment",
            "missionAssignment.mission",
            "missionAssignment.mission.store"
    })
    Page<Review> findByMissionAssignment_Mission_Store(Store store, Pageable pageable);

    // 사용자별 리뷰 목록 조회
    @EntityGraph(attributePaths = {
            "missionAssignment",
            "missionAssignment.member"
    })
    Page<Review> findByMissionAssignment_Member_Id(Long memberId, Pageable pageable);


}
