package com.example.umc9th.domain.notification.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotificationSettingErrorCode implements BaseErrorCode {

    TYPE_CODE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER_NOTIFICATION_SETTING_4001",
            "알 수 없는 알림 코드입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
