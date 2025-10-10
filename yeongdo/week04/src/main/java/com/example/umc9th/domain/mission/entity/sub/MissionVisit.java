package com.example.umc9th.domain.mission.entity.sub;


import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "mission_visit")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@PrimaryKeyJoinColumn(name = "id") // 자식의 pk이자 부모 테이블의 pk를 참조하는 fk 컬럼 명시
public class MissionVisit extends Mission {

    @Builder.Default
    @Column(name = "min_spend", nullable = false)
    private int minSpend = 0;

    @Builder.Default
    @Column(name = "require_owner_code", nullable = false)
    private Boolean requireOwnerCode = true; // 사장님 구분 코드 필요 여부
}