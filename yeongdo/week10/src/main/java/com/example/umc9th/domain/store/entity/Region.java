package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.mission.entity.PointLedger;
import com.example.umc9th.domain.store.enums.RegionCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "region")
@Builder
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_code") // 내부 식별용
    private RegionCode regionCode;

    @Column(name = "name", length = 50, nullable = false) // 외부에 보여주는 이름
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Store> stores = new ArrayList<>();

}
