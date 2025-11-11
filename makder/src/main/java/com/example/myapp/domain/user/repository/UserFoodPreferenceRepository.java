package com.example.myapp.domain.user.repository;

import com.example.myapp.domain.food.entity.FoodCategory;
import com.example.myapp.domain.user.entity.User;
import com.example.myapp.domain.user.entity.UserFoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFoodPreferenceRepository extends JpaRepository<UserFoodPreference, Long> {
    List<UserFoodPreference> findByUser(User user);
    List<UserFoodPreference> findByFoodCategory(FoodCategory foodCategory);
}
