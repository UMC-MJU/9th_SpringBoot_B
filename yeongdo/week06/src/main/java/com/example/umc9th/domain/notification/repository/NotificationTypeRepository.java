package com.example.umc9th.domain.notification.repository;

import com.example.umc9th.domain.notification.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, String> {
}
