package com.example.umc9th.domain.consent.entity;

import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consent_document")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ConsentDocument extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50, nullable=false)
    private String code;

    @Column(name = "version", length = 50, nullable=false)
    private String version;

    @Column(name = "required")
    @Builder.Default
    private Boolean required = true;
}
