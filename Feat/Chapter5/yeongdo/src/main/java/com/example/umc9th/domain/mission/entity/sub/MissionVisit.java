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
@SuperBuilder // 자식 클래스의 builder() 한번으로 부모 클래스의 필드와 잣기 클래스의 필드를 모두 세팅 가능
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@PrimaryKeyJoinColumn(name = "id")
// 자식의 pk이고, 부모(mission.id)를 가리키는 fk라는 것을 명시
// mission_visit.id = mission.id로 1:1 상속 키를 공유. DB 레벨에서 FK 제약으로 무결성 보장
public class MissionVisit extends Mission {

    @Builder.Default
    @Column(name = "min_spend", nullable = false)
    private int minSpend = 0;

    @Builder.Default
    @Column(name = "require_owner_code", nullable = false)
    private Boolean requireOwnerCode = true; // 사장님 구분 코드 필요 여부
}