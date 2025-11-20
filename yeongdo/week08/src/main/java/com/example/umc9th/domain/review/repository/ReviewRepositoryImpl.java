package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.mission.entity.QMission;
import com.example.umc9th.domain.mission.entity.QMissionAssignment;
import com.example.umc9th.domain.review.dto.ReviewCond;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {
    private final JPAQueryFactory qf;

    @Override
    public Page<ReviewResDto.SearchDto> findMyReviews(ReviewCond cond, Pageable pageable){
        QReview r = QReview.review;
        QMissionAssignment ma = QMissionAssignment.missionAssignment;
        QMission m = QMission.mission;
        QStore s = QStore.store;

        // where 조건 조립
        var where = r.missionAssignment.member.id.eq(cond.memberId())
                .and(cond.storeName() != null ? s.name.eq(cond.storeName()) : null)
                .and(cond.rating() != null ? r.rating.goe(cond.rating()) : null);

        // 넘길 내용들
        List<ReviewResDto.SearchDto> content = qf
                // DTO 프로젝션으로 일부 컬럼만 DB 조회 + API 명확하게
                .select(Projections.constructor(ReviewResDto.SearchDto.class, r.title, r.content, r.rating, r.createdAt))
                .from(r)
                .join(r.missionAssignment, ma)
                .join(ma.mission, m)
                .join(m.store, s)
                .where(where)
                .orderBy(r.createdAt.desc(), r.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Page<> 총 개수 필수
        Long total = qf.select(r.count())
                .from(r)
                .join(r.missionAssignment, ma)
                .join(ma.mission, m)
                .join(m.store, s)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
