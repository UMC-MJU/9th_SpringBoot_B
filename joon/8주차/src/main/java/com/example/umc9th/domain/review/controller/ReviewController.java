package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

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

    // 가게에 리뷰 추가하기 API
    @PostMapping("/stores/{storeId}/reviews/register")
    public ApiResponse<ReviewResDto> createReview(@PathVariable Long storeId, @RequestBody @Valid ReviewReqDto.CreateDto dto) {
        ReviewResDto response = reviewCommandService.createReview(storeId, dto);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, response);
    }
}
