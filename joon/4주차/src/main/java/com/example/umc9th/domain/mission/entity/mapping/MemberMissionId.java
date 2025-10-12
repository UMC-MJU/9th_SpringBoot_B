package com.example.umc9th.domain.mission.entity.mapping;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class MemberMissionId implements Serializable {
    @EqualsAndHashCode.Include
    private Long memberId;

    @EqualsAndHashCode.Include
    private Long missionId;
}
