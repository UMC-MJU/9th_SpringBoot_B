package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "address1", length = 100, nullable = false)
    private String address1;

    @Column(name = "address2", length = 100)
    private String address2;

    @Column(name = "owner_secret", length = 20)
    private String ownerSecret; // 방문 시 가게에서 입력해주는 사장님 코드번호
}
