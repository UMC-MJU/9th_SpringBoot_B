package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAddressRepository extends JpaRepository<MemberAddress,Long> {
}
