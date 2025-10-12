package com.example.umc9th.domain.inquiry.entity;

import com.example.umc9th.domain.inquiry.enums.InquiryStatus;
import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inquiry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    @OneToOne(mappedBy = "inquiry", fetch = FetchType.LAZY)
    private InquiryAnswer inquiryAnswer;

    // 초기 설계 때는 answered_at 필드를 두기로 했었는데 InquiryAnswer에 두는 게 맞아보임..
}
