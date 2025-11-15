package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    // 내가 작성한 리뷰 조회 API, 필터링: 가게별, 별점대
    @GetMapping("/reviews/search")
    public ApiResponse<List<ReviewResDto>> searchReview(
            @RequestParam("memberId") Long memberId,
            @RequestParam(value = "storeName", required = false) String storeName,
            @RequestParam(value = "ratingGroup", required = false) Integer rating
    ) {
        List<ReviewResDto> reviewList = reviewQueryService.searchReview(memberId, storeName, rating);

        // ApiResponse.onSuccess로 감싸서 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewList);
    }
}
