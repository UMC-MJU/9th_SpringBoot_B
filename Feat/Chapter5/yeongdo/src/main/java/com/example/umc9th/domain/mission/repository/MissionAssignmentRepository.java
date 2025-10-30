package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment,Long> {
    Optional<MissionAssignment> findByIdAndMemberIdAndStatus(Long id, Long memberId, MissionStatus status);
}
