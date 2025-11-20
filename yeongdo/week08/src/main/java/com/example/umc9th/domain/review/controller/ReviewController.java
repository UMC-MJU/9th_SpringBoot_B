package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewReqDto;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내가 작성한 리뷰 조회
    @GetMapping("/members/{memberId}/reviews")
    ApiResponse<Page<ReviewResDto.SearchDto>> getMyReviews(@PathVariable Long memberId,
                                                           @Valid @ModelAttribute ReviewReqDto.SearchDto dto,
                                                           @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        ReviewCond cond = new ReviewCond(memberId, dto.storeName(), dto.rating());
        Page<ReviewResDto.SearchDto> page = reviewQueryService.findMyReviews(cond,  pageable);
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, page);
    }
    // 가게에 리뷰 추가하기
    // member도 구분을 해야 해서 일단 이렇게 엔드포인트를 구성했습니다!
    @PostMapping("/members/{memberId}/assignments/{assignmentId}/reviews")
    ApiResponse<ReviewResDto.CreateReview> createReview(
            @PathVariable Long memberId,
            @PathVariable Long assignmentId,
            @RequestBody ReviewReqDto.CreateReview req
    ) {

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED,
                reviewCommandService.writeReview(assignmentId, memberId,
                        req.title(), req.content(), req.rating()));
    }

}