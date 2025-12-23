package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.Service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 내가 작성한 리뷰 조회
    @GetMapping("/members/{memberId}/reviews")
    Page<ReviewSummaryDto> getMyReviews(@PathVariable Long memberId,
                                        @RequestParam String storeName,
                                        @RequestParam Float rating,
                                        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return reviewQueryService.findMyReviews(memberId, storeName, rating, pageable);
    }
}
