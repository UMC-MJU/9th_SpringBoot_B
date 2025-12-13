package com.example.umc9th.domain.notification.dto;

public record UpdateSettingRequest(Boolean push, Boolean email, Boolean sms) {
}
