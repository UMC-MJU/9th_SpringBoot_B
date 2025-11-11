package com.example.myapp.domain.user.repository;

import com.example.myapp.domain.challenge.entity.Challenge;
import com.example.myapp.domain.user.entity.User;
import com.example.myapp.domain.user.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findByUser(User user);
    List<UserChallenge> findByChallenge(Challenge challenge);
}
