package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public Page<ReviewResDto.SearchDto> findMyReviews(ReviewCond cond, Pageable pageable) {
        return reviewRepository.findMyReviews(cond, pageable);
    }

}
