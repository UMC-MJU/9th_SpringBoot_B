package com.example.umc9th.domain.notification.dto;


import com.example.umc9th.domain.notification.entity.setting.MemberNotificationSetting;

import java.util.Objects;

public record SettingResponse(String typeCode, boolean push, boolean email, boolean sms) {
    public static SettingResponse from(MemberNotificationSetting s) {
        Objects.requireNonNull(s, "setting");
        return new SettingResponse(
                s.getNotificationType().getTypeCode(),
                s.isPush(),
                s.isEmail(),
                s.isSms()
        );
    }
}
