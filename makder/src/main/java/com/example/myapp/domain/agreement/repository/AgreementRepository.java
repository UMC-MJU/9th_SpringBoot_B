package com.example.myapp.domain.agreement.repository;

import com.example.myapp.domain.agreement.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    Optional<Agreement> findByName(String name);
}
