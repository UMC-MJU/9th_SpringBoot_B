package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 해당 지역의 완료한 미션 달성 갯수
    @Query("""
         SELECT COUNT(mm)
         FROM MemberMission mm
         JOIN mm.member mb
         JOIN mm.mission mn
         JOIN mn.store s
         JOIN s.region r
         WHERE mb.id = :memberId
           AND r.id = :regionId
           AND mm.isCompleted = TRUE
         """)
    Long getCompletedMissionCount(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId
    );

    // 도전 가능한 미션 목록
    @Query("""
         SELECT m
         FROM Mission m
         JOIN m.store s
         JOIN s.region r
         LEFT JOIN m.memberMissionList mm
           WITH mm.member.id = :memberId
         WHERE r.id = :regionId
           AND m.deadline >= CURRENT_DATE
           AND mm.id IS NULL
         ORDER BY m.deadline ASC, m.id ASC
         """)
    Page<Mission> findAvailableMissions(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            Pageable pageable
    );

    // 특정 가게의 미션 목록 조회
    Page<Mission> findMissionByStoreId(Long storeId, Pageable pageable);
}
