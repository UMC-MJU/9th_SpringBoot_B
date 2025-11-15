package com.example.umc9th.domain.notification.service;

import com.example.umc9th.domain.notification.dto.SettingResponse;
import com.example.umc9th.domain.notification.dto.UpdateSettingRequest;

public interface NotificationSettingCommandService {

    SettingResponse updateMySetting(Long memberId,
                                    String typeCode,
                                    UpdateSettingRequest request);
}
