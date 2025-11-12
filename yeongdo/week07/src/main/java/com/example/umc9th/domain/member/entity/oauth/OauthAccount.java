package com.example.umc9th.domain.member.entity.oauth;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Provider;
import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oauth_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OauthAccount extends BaseEntity {

    @Id
    @Column(name = "member_id")
    private Long id;

    @MapsId // Member과 식별관계
    @OneToOne(fetch = FetchType.LAZY, optional = false) // jpa 레벨에서 not null
    @JoinColumn(name = "member_id", nullable = false) // db 레벨에서 not null
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, length = 50)
    private Provider provider; // kakao, google, apple ...

    @Column(name = "provider_user_id", nullable = false, length = 190)
    private String providerUserId; // 카카오 id, 구글 id, 애플id..

}
