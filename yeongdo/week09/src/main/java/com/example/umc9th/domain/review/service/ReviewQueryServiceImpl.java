package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.code.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.code.StoreErrorCode;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    public ReviewResDto.MyReviewPreviewListDto findMyReviews(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).
                orElseThrow( () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 멤버에 맞는 리뷰를 가져옴 (Offset  페이징)
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> myReviews = reviewRepository.findByMissionAssignment_Member_Id(memberId, pageRequest);

        return ReviewConverter.toMyReviewPreviewListDto(myReviews);
    }

    @Override
    public ReviewResDto.ReviewPreviewListDto findStoreReview(String storeName, Integer page) {

        Store store = storeRepository.findByName(storeName)
                .orElseThrow( () -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 가게에 맞는 리뷰를 가져옴 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createAt"));
        Page<Review> result = reviewRepository.findByMissionAssignment_Mission_Store(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDto(result);
    }


}
