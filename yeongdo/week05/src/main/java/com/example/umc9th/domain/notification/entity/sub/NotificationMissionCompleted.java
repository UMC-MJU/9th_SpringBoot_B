package com.example.umc9th.domain.notification.entity.sub;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.notification.entity.Notification;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_mission_completed")
@Getter
@PrimaryKeyJoinColumn(name = "id") // 자식 pk = 부모 pk
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationMissionCompleted extends Notification {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private MissionAssignment missionAssignment;
}
