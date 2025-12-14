package com.example.umc9th.domain.member.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode // 키 객체이기 떄문에 값 동등성을 보장하기 위해서
public class MemberConsentId implements Serializable {
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "consent_id")
    private Long consentId;
}
