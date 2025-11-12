package com.example.umc9th.domain.notification.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class NotificationSettingException extends GeneralException {
    public NotificationSettingException(BaseErrorCode code) {
        super(code);
    }
}
