package com.example.myapp.domain.review.repository;

import com.example.myapp.domain.review.entity.Review;
import com.example.myapp.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findByReview(Review review);
}
