package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.embeddable.MemberRegionProgressId;
import com.example.umc9th.domain.store.entity.Region;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_region_progress")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Builder
public class MemberRegionProgress {

    @EmbeddedId // 부모 pk를 복합 pk로 사용함 (memberId, regionId)
    private MemberRegionProgressId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @MapsId("regionId")
    private Region region;

    @Builder.Default
    @Column(name = "completed_count", nullable = false)
    private int completed_count = 0;

    @LastModifiedDate
    @Column(name = "last_completed_at")
    private LocalDateTime lastCompletedAt;
}
