package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.code.MissionAssignmentErrorCode;
import com.example.umc9th.domain.mission.exception.MissionAssignmentException;
import com.example.umc9th.domain.mission.repository.MissionAssignmentRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final MissionAssignmentRepository missionAssignmentRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResDto.CreateReview writeReview(Long assignmentId, Long memberId, String title, String content, float rating) {
        // 본인이 작성 + COMPLETED인 missionAssignment만 가져옴
        MissionAssignment ma  = missionAssignmentRepository.findByIdAndMemberIdAndStatus(
                assignmentId, memberId, MissionStatus.COMPLETED
        ).orElseThrow( () -> new MissionAssignmentException(MissionAssignmentErrorCode.NOT_COMPLETED));

        // 중복 리뷰 방지
        if (reviewRepository.existsByMissionAssignmentId(assignmentId)) {
            throw new MissionAssignmentException(MissionAssignmentErrorCode.REVIEW_ALREADY_EXISTS);
        }

        // 저장
        Review review = Review.builder().
                missionAssignment(ma).
                title(title)
                .content(content)
                .rating(rating)
                .build();

        // id 받아옴
        review = reviewRepository.save(review);

        return ReviewConverter.toCreateDto(review);
    }

}
