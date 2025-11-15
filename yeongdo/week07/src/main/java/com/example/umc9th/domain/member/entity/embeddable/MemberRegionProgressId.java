package com.example.umc9th.domain.member.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MemberRegionProgressId {
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "region_id", nullable = false)
    private Long regionId;
}
