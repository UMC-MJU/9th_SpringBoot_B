package com.example.umc9th.domain.notification.repository;

import com.example.umc9th.domain.notification.entity.setting.MemberNotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberNotificationSettingRepository extends JpaRepository<MemberNotificationSetting, Long> {
    // memberId와 NotificationType의 typeCode로 멤버별 세팅값을 찾음
    Optional<MemberNotificationSetting> findByMemberIdAndNotificationType_TypeCode(Long memberId, String typeCode);
}
