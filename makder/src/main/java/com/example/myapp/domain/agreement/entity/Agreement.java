package com.example.myapp.domain.agreement.entity;

import com.example.myapp.domain.user.entity.UserAgreement;
import com.example.myapp.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Agreement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "agreement", cascade = CascadeType.ALL)
    private List<UserAgreement> userAgreementList = new ArrayList<>();
}
