package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.res.MissionListDto;
import com.example.umc9th.domain.mission.dto.res.MissionProgressDto;
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
    @Query("SELECT new com.example.umc9th.domain.mission.dto.res.MissionProgressDto(" +
            "mb.id, r.id, COUNT(mm)) " +
            "FROM MemberMission mm " +
            "JOIN mm.member mb " +
            "JOIN mm.mission mn " +
            "JOIN mn.store s " +
            "JOIN s.region r " +
            "WHERE mb.id = :memberId " +
            "AND r.id = :regionId " +
            "AND mm.isCompleted = TRUE " +
            "GROUP BY mb.id, r.id")
    MissionProgressDto getCompletedMissionCount(@Param("memberId") Long memberId, @Param("regionId") Long regionId);

    // 도전 가능한 미션 목록
    @Query("SELECT new com.example.umc9th.domain.mission.dto.res.MissionListDto(" +
            "m.id, s.storeName, m.missionName, m.rewardPoint, " +
            "DATEDIFF(m.deadline, CURRENT_TIMESTAMP)) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.region r " +
            "LEFT JOIN MemberMission mm " +
            "ON m.id = mm.mission.id AND mm.member.id = :memberId " +
            "WHERE r.id = :regionId " +
            "AND m.deadline >= CURRENT_DATE " +
            "AND mm.id IS NULL " +
            "ORDER BY m.deadline ASC, m.id ASC")
    Page<MissionListDto> findAvailableMissions(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            Pageable pageable);
}
