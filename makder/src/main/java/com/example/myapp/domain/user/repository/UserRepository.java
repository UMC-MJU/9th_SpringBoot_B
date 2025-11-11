package com.example.myapp.domain.user.repository;

import com.example.myapp.domain.user.entity.User;
import com.example.myapp.global.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method name query to find a user by email
    Optional<User> findByEmail(String email);

    // Method name query to find users by status
    List<User> findByStatus(UserStatus status);

    // @Query annotation to find active users
    @Query("select u from User u where u.status = 'ACTIVE'")
    List<User> findActiveUsers();

    // @Query annotation with parameters
    @Query("select u from User u where u.email = :email and u.status = :status")
    Optional<User> findByEmailAndStatus(@Param("email") String email, @Param("status") UserStatus status);
}
