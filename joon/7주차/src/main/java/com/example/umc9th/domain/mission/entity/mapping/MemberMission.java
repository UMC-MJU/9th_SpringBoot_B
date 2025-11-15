package com.example.umc9th.domain.mission.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
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
    @ToString.Exclude
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("missionId")
    @JoinColumn(name = "mission_id")
    @ToString.Exclude
    @JsonIgnore
    private Mission mission;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted = false;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    // 정적 팩토리 메서드
    public static MemberMission createMemberMission(Member member, Mission mission) {
        MemberMission memberMission = new MemberMission();
        memberMission.id = new MemberMissionId(member.getId(), mission.getId());
        memberMission.member = member;
        memberMission.mission = mission;
        memberMission.isCompleted = false; // 기본값은 미완료
        return memberMission;
    }

    // 미션 완료 처리 메서드
    public void complete() {
        this.isCompleted = true;
        this.completedAt = LocalDateTime.now();
    }
}
