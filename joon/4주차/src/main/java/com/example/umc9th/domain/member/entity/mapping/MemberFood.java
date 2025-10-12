package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_food")
public class MemberFood {
    @EmbeddedId
    private MemberFoodId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;
}
