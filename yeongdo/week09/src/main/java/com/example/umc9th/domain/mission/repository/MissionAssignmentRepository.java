package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment,Long> {
    Optional<MissionAssignment> findByIdAndMember_IdAndStatus(Long id, Long memberId, MissionStatus status);

    // MissionDto로 변환 시 mission, mission.store에 접근하고, toOne이라 EntityGraph 달아둠
    @EntityGraph(attributePaths = {"mission", "mission.store"})
    Page<MissionAssignment> findByMember_IdAndStatusOrderByStartedAtDesc(
            Long memberId, MissionStatus status, Pageable pageable
    );

    boolean existsByMember_IdAndMission_IdAndStatus(Long memberId, Long missionId, MissionStatus status);

    // 선택한 지역에서 완료한 미션 개수 보는 쿼리 (status에 COMPLETED)
    @Query("""
    select count(ma)
    from MissionAssignment ma
    where ma.member.id = :memberId
        and ma.mission.region.id = :regionId
        and ma.status = :status

""")
    Long countCompletedInRegion(Long memberId, Long regionid, MissionStatus status);

    // 이미 종료된 미션 제외, 이미 참여했던 미션 제외하고 미션 목록 페이징
    @Query(
    value = """
    select m
    from Mission m
    where m.region.id = :regionId
        and (m.endsAt is null or m.endsAt >= CURRENT_TIMESTAMP) 
        and not exists (
        select 1
        from MissionAssignment ma
        where ma.member.id = :memberId
            and m.id = ma.mission.id
        )
    order by m.id desc
""",
            countQuery = """
  select count(m)
  from Mission m
  where m.region.id = :regionId
    and (m.endsAt is null or m.endsAt >= CURRENT_TIMESTAMP)
    and not exists (
      select 1 from MissionAssignment ma
      where ma.member.id = :memberId
        and ma.mission.id = m.id
    )
  """
    )
    Page<Mission> findAvailableMissions(Long memberId, Long regionId, Pageable pageable);
}
