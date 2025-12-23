package com.example.umc9th.domain.member.entity;

import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class MemberAddress extends BaseEntity {
    @Id
    private Long id; // PK = FK

    @MapsId // 이 연관의 FK(member_id) = 이 엔티티의 PK
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id") // 실제 FK 컬럼
    private Member member;

    @Column(name = "postal_code", length=20, nullable=false)
    private String postalCode;

    @Column(name = "address1", length = 50, nullable = false)
    private String addr1;

    @Column(name = "address2", length = 50)
    private String addr2;
}
