package com.example.umc9th.domain.mission.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
public class MemberMission {
    @EmbeddedId
    private MemberMissionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("missionId")
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "is_completed", nullable = false)
    @Builder.Default
    private boolean isCompleted = false;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
