package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ReviewControllerDocs {
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 준 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(String storeName, Integer page);

    @Operation(
            summary = "내가 쓴 리뷰 목록 조회 API",
            description = "회원이 작성한 리뷰 목록을 1페이지 당 10개씩 조회합니다. page는 1 이상만 허용됩니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "검증 실패 또는 기타 오류")
    })
    ApiResponse<ReviewResDto.MyReviewPageDto> getMyReviews(Long memberId, @ValidPage Integer page);
}
