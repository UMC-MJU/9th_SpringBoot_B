package com.example.umc9th.domain.notification.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.notification.entity.NotificationType;
import com.example.umc9th.domain.notification.entity.setting.MemberNotificationSetting;
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
public class NotificationSettingService {

    private final MemberNotificationSettingRepository settingRepo;
    private final NotificationTypeRepository typeRepo;
    private final MemberRepository memberRepo;

    // 부분 업데이트용 DTO
    @Getter
    public static class UpdateSettingRequest{
        private Boolean push;
        private Boolean email;
        private Boolean sms;
    }

    @Getter
    @AllArgsConstructor
    public static class SettingResponse{
        private String typeCode;
        private boolean push;
        private boolean email;
        private boolean sms;
        public static SettingResponse from(MemberNotificationSetting s) {
            return new SettingResponse(
                    s.getNotificationType().getTypeCode(),
                    s.isPush(),
                    s.isEmail(),
                    s.isSms());
        }

    }

    public SettingResponse updateMySetting(Long memberId, String typeCode, UpdateSettingRequest request) {
        MemberNotificationSetting s = settingRepo.findByMemberIdAndNotificationType_TypeCode(memberId, typeCode)
                .orElseGet( () -> createWithDefaults(memberId, typeCode));

        if (request.getPush() != null) {s.setPush(request.getPush());}
        if (request.getEmail() != null) {s.setEmail(request.getEmail());}
        if (request.getSms() != null) {s.setSms(request.getSms());}

        return SettingResponse.from(settingRepo.save(s));
    }

    public MemberNotificationSetting createWithDefaults(Long memberId, String typeCode) {
        Member m = memberRepo.getReferenceById(memberId); // DB에 바로 접근하지 않고 프록시 반환 (실제 필드 접근하거나 플러시 시점에 필요해지면 그때 조회함)
        NotificationType type = typeRepo.findById(typeCode).orElseThrow(
                () -> new IllegalArgumentException("타입코드 에러 : " + typeCode) );

        MemberNotificationSetting setting =  new MemberNotificationSetting(
                null, m, type, type.isDefaultPush(), type.isDefaultEmail(), type.isDefaultSms()
        );

        return settingRepo.save(setting);
    }
}
