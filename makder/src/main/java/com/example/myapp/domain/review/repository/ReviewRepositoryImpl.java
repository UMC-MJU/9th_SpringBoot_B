package com.example.myapp.domain.review.repository;

import com.example.myapp.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.myapp.domain.review.entity.QReview.review;
import static com.example.myapp.domain.shop.entity.QShop.shop;
import static com.example.myapp.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviews(Long userId, Long storeId, Integer star, Pageable pageable) {
        // 데이터 콘텐츠 조회
        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.shop, shop).fetchJoin() // N+1 문제 방지를 위한 fetchJoin
                .join(review.user, user).fetchJoin()
                .where(
                        user.id.eq(userId), // 기본 조건: 현재 사용자
                        storeIdEq(storeId),   // 동적 조건 1: 가게 ID
                        starMatches(star)     // 동적 조건 2: 별점
                )
                .orderBy(review.createdAt.desc()) // 최신순으로 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 카운트 쿼리
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        review.user.id.eq(userId),
                        storeIdEq(storeId),
                        starMatches(star)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    // == 동적 조건 메서드 == //

    /**
     * 가게 ID 조건
     * storeId가 null이 아니면 해당 가게의 리뷰만 필터링
     */
    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.shop.id.eq(storeId) : null;
    }

    /**
     * 별점 조건
     * star가 null이 아니면 해당 점수대의 리뷰만 필터링
     * 5점 -> 5.0
     * 4점 -> 4.0 <= star < 5.0
     * 3점 -> 3.0 <= star < 4.0
     */
    private BooleanExpression starMatches(Integer star) {
        if (star == null) {
            return null;
        }
        if (star == 5) {
            return review.star.eq(5.0);
        }
        // star.doubleValue()를 사용하여 정수를 double로 변환
        return review.star.goe(star.doubleValue()).and(review.star.lt(star + 1.0));
    }
}
