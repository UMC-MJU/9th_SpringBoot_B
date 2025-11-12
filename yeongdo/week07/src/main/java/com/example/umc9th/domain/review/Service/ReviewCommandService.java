package com.example.umc9th.domain.review.Service;

public interface ReviewCommandService {
    Long writeReview(Long assignmentId, Long memberId, String title, String content, int rating);
}
