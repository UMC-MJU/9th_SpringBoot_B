package com.example.umc9th.domain.review.Service;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.MissionAssignmentRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewService {
    private final MissionAssignmentRepository missionAssignmentRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long writeReview(Long assignmentId, Long memberId, String title, String content, int rating) {
        // 본인이 작성 + COMPLETED인 missionAssignment만 가져옴
        MissionAssignment ma  = missionAssignmentRepository.findByIdAndMemberIdAndStatus(
                assignmentId, memberId, MissionStatus.COMPLETED
        ).orElseThrow( () -> new IllegalStateException("작성 조건을 만족하지 않는 미션입니다"));

        // 중복 리뷰 방지
        if (reviewRepository.existsByMissionAssignmentId(assignmentId)) {
            throw new IllegalStateException("이미 이 미션에 대한 리뷰가 존재합니다.");
        }

        // 저장
        Review review = Review.builder().
                missionAssignment(ma).
                title(title)
                .content(content)
                .rating(rating)
                .build();
        reviewRepository.save(review);

        return review.getId();
    }

}
