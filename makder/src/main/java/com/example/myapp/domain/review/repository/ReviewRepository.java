package com.example.myapp.domain.review.repository;

import com.example.myapp.domain.review.entity.Review;
import com.example.myapp.domain.shop.entity.Shop;
import com.example.myapp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Method name query to find reviews by user
    List<Review> findByUser(User user);

    // Method name query to find reviews by shop
    List<Review> findByShop(Shop shop);

    // Method name query to find reviews with a rating greater than a given value
    List<Review> findByRatingGreaterThan(Float rating);

    // Method name query to find reviews for a specific shop and user
    List<Review> findByShopAndUser(Shop shop, User user);
}
