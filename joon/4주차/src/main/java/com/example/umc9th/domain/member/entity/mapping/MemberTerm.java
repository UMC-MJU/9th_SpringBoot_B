package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.Term;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_term")
public class MemberTerm {
    @EmbeddedId
    private MemberTermId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("termId")
    @JoinColumn(name = "term_id")
    @ToString.Exclude
    @JsonIgnore
    private Term term;
}
