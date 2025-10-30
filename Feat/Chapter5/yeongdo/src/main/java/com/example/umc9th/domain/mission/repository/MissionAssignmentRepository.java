package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment,Long> {
    Optional<MissionAssignment> findByIdAndMemberIdAndStatus(Long id, Long memberId, MissionStatus status);

    // MissionDto로 변환 시 mission, mission.store에 접근하고, toOne이라 EntityGraph 달아둠
    @EntityGraph(attributePaths = {"mission", "mission.store"})
    Page<MissionAssignment> findByMember_IdAndStatusOrderByStartedAtDesc(
            Long memberId, MissionStatus status, Pageable pageable
    );
}
