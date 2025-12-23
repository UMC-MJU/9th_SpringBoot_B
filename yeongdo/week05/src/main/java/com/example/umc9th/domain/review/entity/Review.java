package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
// columnNames는 DB 기준 컬럼명
@Table(name = "review",
        uniqueConstraints = @UniqueConstraint(name = "uk_review_assignment", columnNames = "assignment_id" ))

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private MissionAssignment missionAssignment;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "rating", nullable = false)
    private int rating;

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
    private ReviewReply reviewReply;

}
