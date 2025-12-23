package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberConsent;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberRegionProgress;
import com.example.umc9th.domain.member.entity.onboarding.OnboardingState;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.MemberStatus;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.notification.entity.setting.MemberNotificationSetting;
import com.example.umc9th.global.BaseEntity;
import com.example.umc9th.global.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "member")

public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 5, nullable = false)
    private String name;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberAddress address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    MemberStatus memberStatus = MemberStatus.ACTIVE;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birth;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private OnboardingState onboardingState;

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    // 집계의 성격이 있는 것 같아서 부모 저장할 때 함께 저장할 필요는 없어보임
    private List<MissionAssignment> missionAssignments = new ArrayList<>();

    @Builder.Default // 빌더로 객체 만들면 초기값 무시해서 넣어두기
    @OneToMany(mappedBy =  "member", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true) // 비주인 삭제해도 삭제되도록
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberConsent> memberConsentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberNotificationSetting> memberNotificationSettingList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberRegionProgress> memberRegionProgressList = new ArrayList<>();

    // 연관관계 편의 메서드
    // MemberFoodList


    public void addMemberFood(MemberFood memberFood) {
        memberFoodList.add(memberFood);
        memberFood.setMember(this);
    }
    public void removeMemberFood(MemberFood memberFood) {
        memberFoodList.remove(memberFood);
    }

    public void setMemberFoodList(List<MemberFood> memberFoodList) {
        for(MemberFood memberFood : memberFoodList) {
            this.addMemberFood(memberFood);
        }
    }

    // MemberConsentList
    public void addMemberConsent(MemberConsent memberConsent) {
        memberConsentList.add(memberConsent);
        memberConsent.setMember(this);
    }
    public void removeMemberConsent(MemberConsent memberConsent) {
        memberConsentList.remove(memberConsent);
    }

    // MemberRegionProgress
    public void addMemberRegionProgress(MemberRegionProgress memberRegionProgress) {
        memberRegionProgressList.add(memberRegionProgress);
        memberRegionProgress.setMember(this);
    }
    public void removeMemberRegionProgress(MemberRegionProgress memberRegionProgress) {
        memberRegionProgressList.remove(memberRegionProgress);
    }

    //MemberAddress
    public void setAddress(MemberAddress address) {
        this.address = address;
        this.address.setMember(this);
    }
}
