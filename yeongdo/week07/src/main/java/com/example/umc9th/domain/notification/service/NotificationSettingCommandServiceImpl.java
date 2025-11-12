package com.example.umc9th.domain.notification.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.notification.dto.SettingResponse;
import com.example.umc9th.domain.notification.dto.UpdateSettingRequest;
import com.example.umc9th.domain.notification.entity.NotificationType;
import com.example.umc9th.domain.notification.entity.setting.MemberNotificationSetting;
import com.example.umc9th.domain.notification.exception.NotificationSettingErrorCode;
import com.example.umc9th.domain.notification.exception.NotificationSettingException;
import com.example.umc9th.domain.notification.repository.MemberNotificationSettingRepository;
import com.example.umc9th.domain.notification.repository.NotificationTypeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationSettingCommandServiceImpl {

    private final MemberNotificationSettingRepository settingRepo;
    private final NotificationTypeRepository typeRepo;
    private final MemberRepository memberRepo;


    public SettingResponse updateMySetting(Long memberId, String typeCode, UpdateSettingRequest request) {
        MemberNotificationSetting s = settingRepo.findByMemberIdAndNotificationType_TypeCode(memberId, typeCode)
                .orElseGet( () -> createWithDefaults(memberId, typeCode));

        if (request.push() != null) {s.setPush(request.push());}
        if (request.email() != null) {s.setEmail(request.email());}
        if (request.sms() != null) {s.setSms(request.sms());}

        return SettingResponse.from(settingRepo.save(s));
    }

    // 내부 헬퍼 (인터페이스에 노출 X)
    public MemberNotificationSetting createWithDefaults(Long memberId, String typeCode) {
        Member m = memberRepo.getReferenceById(memberId); // DB에 바로 접근하지 않고 프록시 반환 (실제 필드 접근하거나 플러시 시점에 필요해지면 그때 조회함)
        NotificationType type = typeRepo.findById(typeCode).orElseThrow(
                () -> new NotificationSettingException(NotificationSettingErrorCode.TYPE_CODE_NOT_FOUND));

        MemberNotificationSetting setting =  new MemberNotificationSetting(
                null, m, type, type.isDefaultPush(), type.isDefaultEmail(), type.isDefaultSms()
        );

        return settingRepo.save(setting);
    }
}
