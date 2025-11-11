package com.example.myapp.domain.review.repository;

import com.example.myapp.domain.review.entity.Reply;
import com.example.myapp.domain.review.entity.Review;
import com.example.myapp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByUser(User user);
    List<Reply> findByReview(Review review);
}
