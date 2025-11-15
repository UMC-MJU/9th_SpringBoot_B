package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.res.MemberMissionDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMissionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, MemberMissionId> {
    // 진행중, 완료한 미션 목록
    @Query("SELECT new com.example.umc9th.domain.mission.dto.res.MemberMissionDto(" +
            "mm.id, m.rewardPoint, s.storeName, m.missionName, mm.isCompleted) " +
            "FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "JOIN m.store s " +
            "WHERE mm.member.id = :memberId")
    Page<MemberMissionDto> findMyMissionList(@Param("memberId") Long memberId, Pageable pageable);
}
