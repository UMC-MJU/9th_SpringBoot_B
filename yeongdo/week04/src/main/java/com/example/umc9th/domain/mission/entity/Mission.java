package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.enums.MissionType;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder // 상속 구조에서 부모, 자식 필드 모두 포함한 빌더를 위해 (부모, 자식 둘 다 달아야 함)
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
    private List<MissionAssignment> missionAssignments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "mission_type", nullable = false, length = 20)
    private MissionType type = MissionType.VISIT;

    @Column(name = "title", length = 120, nullable = false)
    private String title;

    @Column(name = "description")
    @Lob // 큰 문자 데이터(CLOB/텍스트)
    private String description;

    @Column(name = "ends_at")
    private LocalDateTime endsAt;

    @Column(name = "base_reward", nullable = false)
    private int baseReward;
}
