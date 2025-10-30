package com.example.umc9th.domain.notification.entity.setting;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.notification.entity.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_notification_setting",
uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "type_code"})
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberNotificationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="type_code", nullable = false, referencedColumnName = "type_code") // 문자열 pk라 안전하게 지정
    private NotificationType notificationType;

    @Column(name = "push", nullable = false)
    private boolean push;

    @Column(name = "email", nullable = false)
    private boolean email;

    @Column(name = "sms", nullable = false)
    private boolean sms;
}
