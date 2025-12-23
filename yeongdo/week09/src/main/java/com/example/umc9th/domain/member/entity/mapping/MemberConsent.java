package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.consent.entity.ConsentDocument;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.embeddable.MemberConsentId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "member_consent")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class MemberConsent {

    // 복합 pk라 값 타입으로 선언
    @EmbeddedId
    private MemberConsentId id;

    @MapsId("memberId") // pk와 fk가 같은 컬럼임
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @MapsId("consentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consent_id", nullable = false)
    private ConsentDocument consent;

    @CreatedDate
    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt;

}
