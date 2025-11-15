package com.example.umc9th.domain.member.entity.mapping;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class MemberFoodId implements Serializable {
    @EqualsAndHashCode.Include
    private Long memberId;

    @EqualsAndHashCode.Include
    private Long foodId;
}
