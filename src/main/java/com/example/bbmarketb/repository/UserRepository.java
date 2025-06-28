package com.example.bbmarketb.repository;

import com.example.bbmarketb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기능 선언
    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
    Optional<User> findByPassword(String password);


}
