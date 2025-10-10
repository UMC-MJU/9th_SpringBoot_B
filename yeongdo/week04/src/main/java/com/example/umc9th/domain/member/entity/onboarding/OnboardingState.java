package com.example.umc9th.domain.member.entity.onboarding;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.OnboardingStage;
import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "onboarding_state")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OnboardingState extends BaseEntity {

    @Id
    @Column(name = "member_id")
    private Long id;

    @MapsId // 식별관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name ="stage", nullable = false)
    @Enumerated(EnumType.STRING)
    private OnboardingStage stage;

    @Column(name = "last_step")
    @Enumerated(EnumType.STRING)
    private OnboardingStage lastStage;

    public OnboardingState(Member member, OnboardingStage initial) {
        this.member = member;
        this.id = member.getId();
        this.stage = initial;
    }

    // 최근 진행 단계 저장 후 다음 단계로 이동
    public void nextStage(OnboardingStage nextStage) {
        this.lastStage = this.stage;
        this.stage = nextStage;
    }
}
