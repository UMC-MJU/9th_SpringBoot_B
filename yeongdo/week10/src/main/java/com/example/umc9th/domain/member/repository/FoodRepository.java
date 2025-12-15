package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.enums.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByFoodCategory(FoodCategory foodCategory);
}
