package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewReqDto;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내가 작성한 리뷰 조회
    @Override
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDto.MyReviewPreviewListDto> getMyReviews(@PathVariable Long memberId,
                                                                               @RequestParam(required = false) @CheckPage Integer page) {
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewQueryService.findMyReviews(memberId, page));
    }
    // 가게에 리뷰 추가하기
    // member도 구분을 해야 해서 일단 이렇게 엔드포인트를 구성했습니다!
    @PostMapping("/members/{memberId}/assignments/{assignmentId}/reviews")
    public ApiResponse<ReviewResDto.CreateReview> createReview(
            @PathVariable Long memberId,
            @PathVariable Long assignmentId,
            @RequestBody ReviewReqDto.CreateReview req
    ) {

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED,
                reviewCommandService.writeReview(assignmentId, memberId,
                        req.title(), req.content(), req.rating()));
    }

    // 가게의 리뷰 목록 조회
    @Override
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDto.ReviewPreviewListDto> getReviews(
            @RequestParam String storeName,
            @RequestParam(required = false) @CheckPage Integer page
    ) {

        ReviewSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewQueryService.findStoreReview(storeName, page));
    }

}