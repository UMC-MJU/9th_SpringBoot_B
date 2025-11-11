package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewReply extends BaseEntity {
    @Id
    @Column(name = "review_id")
    private Long id; // 부모 pk(reviewId) = 자식 fk

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // review와 식별관계
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;
}
