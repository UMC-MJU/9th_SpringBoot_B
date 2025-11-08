package com.example.myapp.domain.challenge.repository;

import com.example.myapp.domain.challenge.entity.Challenge;
import com.example.myapp.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByShop(Shop shop);
}
