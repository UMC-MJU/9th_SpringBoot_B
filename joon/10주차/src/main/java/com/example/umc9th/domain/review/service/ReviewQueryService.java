package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 내가 작성한 리뷰 조회 API, 필터링: 가게별, 별점대
    public List<ReviewResDto.MyReviewDto> searchReview(Long memberId, String storeName, Integer rating) {
        BooleanBuilder builder = new BooleanBuilder();
        QReview review = QReview.review;

        // 내가 작성한 리뷰
        builder.and(review.member.id.eq(memberId));

        // 가게명 필터
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.storeName.containsIgnoreCase(storeName));
        }

        // 별점 필터
        if (rating != null) {
            int lower = rating;
            int upper = rating + 1;
            builder.and(review.rating.goe(lower).and(review.rating.lt(upper)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 검색 결과가 비어있으면 예외 발생
        if (reviewList.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        return reviewList.stream()
                .map(ReviewResDto.MyReviewDto::from)
                .collect(Collectors.toList());
    }

    // 가게의 리뷰 목록 조회
    public ReviewResDto.ReviewPreViewListDto findReview(String storeName, Integer page) {
        Store store = storeRepository.findBystoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    // 내가 작성한 리뷰 목록 조회 (페이징, 한 페이지 10개)
    public ReviewResDto.MyReviewPageDto findMyReviews(Long memberId, Integer page) {
        int pageIndex = page - 1;

        PageRequest pageRequest = PageRequest.of(pageIndex, 10);

        Page<Review> result = reviewRepository.findAllByMemberId(memberId, pageRequest);

        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        return ReviewConverter.toMyReviewPageDto(result);
    }
}
