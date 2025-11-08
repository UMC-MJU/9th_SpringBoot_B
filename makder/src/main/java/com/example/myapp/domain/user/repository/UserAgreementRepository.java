package com.example.myapp.domain.user.repository;

import com.example.myapp.domain.agreement.entity.Agreement;
import com.example.myapp.domain.user.entity.User;
import com.example.myapp.domain.user.entity.UserAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long> {
    List<UserAgreement> findByUser(User user);
    List<UserAgreement> findByAgreement(Agreement agreement);
}
