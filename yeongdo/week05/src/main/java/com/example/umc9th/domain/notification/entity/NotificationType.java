package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.notification.entity.setting.MemberNotificationSetting;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "notification_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class NotificationType {
    @Id
    @Column(name = "type_code", length = 50)
    private String typeCode;

    // DB 설계할 때는 on delete cascade로 뒀었는데, 타입을 삭제한다고 알림을 삭제하면 안될 것 같아 수정
    @OneToMany(mappedBy = "notificationType", fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "notificationType", fetch = FetchType.LAZY)
    private List<MemberNotificationSetting> memberNotificationSettingList;

    @Builder.Default
    @Column(name = "default_push", nullable = false)
    private boolean defaultPush = true;

    @Column(name = "default_email")
    private boolean defaultEmail;

    @Column(name = "default_sms")
    private boolean defaultSms;
}
