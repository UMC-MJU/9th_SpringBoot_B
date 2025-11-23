package com.example.myapp.domain.review.controller;

import com.example.myapp.domain.review.entity.Review;
import com.example.myapp.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 내가 작성한 리뷰 목록 조회 API (동적 쿼리)
     *
     * @param storeId  (Optional) 필터링할 가게 ID
     * @param star     (Optional) 필터링할 별점 (4 -> 4점대, 5 -> 5점)
     * @param pageable (Optional) 페이징 정보 (예: ?page=0&size=10)
     * @return 페이징된 리뷰 목록
     */
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 동적 쿼리를 사용하여 조회합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID"),
            @Parameter(name = "star", description = "별점"),
            @Parameter(name = "pageable", description = "페이징 정보")
    })
    @GetMapping("/my")
    public ResponseEntity<Page<Review>> getMyReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer star,
            @PageableDefault(size = 10) Pageable pageable) {

        // TODO: Spring Security의 Authentication 객체나 @AuthenticationPrincipal을 사용하여
        //       실제 로그인한 사용자의 ID를 가져와야 합니다.
        // 예: UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //     Long currentUserId = userService.findUserByUsername(userDetails.getUsername()).getId();
        Long currentUserId = 1L; // 임시 사용자 ID

        Page<Review> reviewPage = reviewService.getMyReviews(currentUserId, storeId, star, pageable);

        // 참고: 실제 프로젝트에서는 Entity를 직접 반환하기보다,
        // 필요한 데이터만 담은 DTO(Data Transfer Object)로 변환하여 반환하는 것이 좋습니다.
        // 예: Page<ReviewResponseDto> responseDtoPage = reviewPage.map(ReviewResponseDto::from);
        return ResponseEntity.ok(reviewPage);
    }
}
